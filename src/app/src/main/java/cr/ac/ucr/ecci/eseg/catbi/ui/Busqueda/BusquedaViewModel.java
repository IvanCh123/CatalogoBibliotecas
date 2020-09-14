package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusquedaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BusquedaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento búsqueda");
    }

    public LiveData<String> getText() {
        return mText;
    }
}