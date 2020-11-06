package cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecycleViewBibliotecaConfig;

public class UbicacionFragment extends Fragment {
    View v;
    Context contexto;
    private DataBaseHelperRoom dbLocalHelper;

    private UbicacionViewModel dashboardViewModel;
    private RecyclerView BibliotecaConfig;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_ubicacion,container,false);
        contexto=getActivity();

        dashboardViewModel = ViewModelProviders.of(this).get(UbicacionViewModel.class);

        BibliotecaConfig = v.findViewById(R.id.recycler_bibliotecas);

        if(hayConexionAInternet()) {
            new FireBaseDataBaseBiblitecaHelper().readBibliotecas(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
                @Override
                public void dataLoaded(List<Biblioteca> listaBibliotecas, List<String> keys) {
                    Log.d("Tq", String.valueOf(listaBibliotecas.size()));
                    new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto, listaBibliotecas, keys,getActivity());
                }
            });
        }else{
            dbLocalHelper = new DataBaseHelperRoom(getContext());
            dbLocalHelper.readBibliotecas(new DataBaseHelperRoom.BibliotecaDataStatus() {
                @Override
                public void DataIsLoaded(List<Biblioteca> bibliotecas) {
                    int tamanoLista = bibliotecas.size();
                    List<String> keys = new ArrayList<>();

                    for(int i =0; i < tamanoLista; i++){
                        keys.add(String.valueOf(i));
                    }
                    new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto, bibliotecas, keys, getActivity());
                }
            });
        }

        return v;
    }
    

    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}