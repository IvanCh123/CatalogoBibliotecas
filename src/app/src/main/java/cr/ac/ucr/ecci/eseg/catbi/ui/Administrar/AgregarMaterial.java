package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class AgregarMaterial extends AppCompatActivity {
    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"aqui",Toast.LENGTH_SHORT).show();
        Log.v("w","2");
        setContentView(R.layout.activity_agregar_material);
        final EditText editTextTituloMaterial =  (EditText) findViewById(R.id.add_titulo);
        final EditText editTextAutorMaterial =  (EditText) findViewById(R.id.add_autores);
        final EditText editTextIdiomaMaterial =  (EditText) findViewById(R.id.add_idioma);
        final EditText editTextTipoMaterial =  (EditText) findViewById(R.id.add_tipo_mat);
        final TextView txtColeccion = (TextView) findViewById(R.id.menuColeccion_textView);
        //final EditText editTextTituloMaterial =  (EditText) findViewById(R.id.add_titulo);
        final Button btn_agregar=(Button)findViewById(R.id.btn_add_material);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //agregar a la base de datos
            }
        });
        Log.v("w","5");
    }
}
