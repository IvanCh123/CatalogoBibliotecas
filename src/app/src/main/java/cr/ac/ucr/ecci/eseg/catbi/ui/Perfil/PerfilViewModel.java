package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PerfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de preguntas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}