package cr.ac.ucr.ecci.eseg.catbi.ui.Acceso;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccesoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccesoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de preguntas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}