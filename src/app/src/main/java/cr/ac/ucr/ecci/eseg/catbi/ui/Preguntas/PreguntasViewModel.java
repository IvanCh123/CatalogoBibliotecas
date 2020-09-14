package cr.ac.ucr.ecci.eseg.catbi.ui.Preguntas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreguntasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PreguntasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de preguntas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}