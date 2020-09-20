package cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.ListarBibliotecas;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecycleViewBibliotecaConfig;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel dashboardViewModel;
    private RecyclerView BibliotecaConfig;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ubicacion,container,false);
        final Context contexto=getActivity();

        BibliotecaConfig = v.findViewById(R.id.recycler_bibliotecas);
        new FireBaseDataBaseBiblitecaHelper().readBibliotecas(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
            @Override
            public void dataLoaded(List<ListarBibliotecas> ListaBibliotecas, List<String> keys) {
                new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto,ListaBibliotecas,keys);
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

        dashboardViewModel = ViewModelProviders.of(this).get(UbicacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubicacion, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}