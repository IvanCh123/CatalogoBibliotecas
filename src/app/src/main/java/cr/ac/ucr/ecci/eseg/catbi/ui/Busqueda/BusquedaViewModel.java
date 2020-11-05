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
    }

    public LiveData<String> getText() {
        return mText;
    }
}