package cr.ac.ucr.ecci.eseg.catbi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;


public class ResultadosBusqueda extends AppCompatActivity {

    public final static String MESSAGE_KEY ="palabraKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);
        TextView textView = (TextView) findViewById(R.id.txtPalabra);
        textView.setText(message);

    }


}