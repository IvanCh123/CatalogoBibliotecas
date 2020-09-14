package cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbicacionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UbicacionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de ubicacion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}