package cr.ac.ucr.ecci.eseg.catbi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

public class InformacionDetalladaMaterial extends AppCompatActivity {
    private static final String TAG = "InformacionDetalladaMaterial";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_detallada_material);
        Log.d(TAG, "onCreate: called.");
    }
}