package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.ListarBibliotecas;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecycleViewBibliotecaConfig;

public class BusquedaViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private RecyclerView recyclerView;

    public BusquedaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("En proceso");
        /*recyclerView=(RecyclerView) recyclerView.findViewById(R.id.recycler_bibliotecas);
        new FireBaseDataBaseBiblitecaHelper().readBibliotecas(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
            @Override
            public void dataLoaded(List<ListarBibliotecas> ListaBibliotecas, List<String> keys) {
                new RecycleViewBibliotecaConfig().setConfig(recyclerView,BusquedaViewModel.this,ListaBibliotecas,keys);
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
        });*/
    }

    public LiveData<String> getText() {
        return mText;
    }
}