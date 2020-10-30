package cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecycleViewBibliotecaConfig;

public class UbicacionFragment extends Fragment {
    View v;
    Context contexto;

    private UbicacionViewModel dashboardViewModel;
    private RecyclerView BibliotecaConfig;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_ubicacion,container,false);
        contexto=getActivity();



        dashboardViewModel = ViewModelProviders.of(this).get(UbicacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubicacion, container, false);

        BibliotecaConfig = v.findViewById(R.id.recycler_bibliotecas);
        Log.d("Tq", String.valueOf(BibliotecaConfig));
        new FireBaseDataBaseBiblitecaHelper().readBibliotecas(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
            @Override
            public void dataLoaded(List<Biblioteca> listaBibliotecas, List<String> keys) {
                Log.d("Tq", String.valueOf(listaBibliotecas.size()));
                new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto,listaBibliotecas,keys);
            }

            @Override
            public void dataInserted() {

            }

            @Override
            public void dataDeleted() {

            }

            @Override
            public void dataUpdated() {

            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BibliotecaConfig = v.findViewById(R.id.recycler_bibliotecas);
        Log.d("Tq", String.valueOf(BibliotecaConfig));
        new FireBaseDataBaseBiblitecaHelper().readBibliotecas(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
            @Override
            public void dataLoaded(List<Biblioteca> listaBibliotecas, List<String> keys) {
                Log.d("Tq", String.valueOf(listaBibliotecas.size()));
                new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto,listaBibliotecas,keys);
            }

            @Override
            public void dataInserted() {

            }

            @Override
            public void dataDeleted() {

            }

            @Override
            public void dataUpdated() {

            }
        });

    }

}