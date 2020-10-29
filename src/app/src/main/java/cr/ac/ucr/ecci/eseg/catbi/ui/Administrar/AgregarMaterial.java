package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;

public class AgregarMaterial extends AppCompatActivity {
    View v;
    private ColeccionOnClick coleccionOnClick;
    private BibliotecanOnClick bibliotecaOnClick;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_material);
        final EditText editTextTituloMaterial =  (EditText) findViewById(R.id.add_titulo);
        final EditText editTextAutorMaterial =  (EditText) findViewById(R.id.add_autores);
        final EditText editTextIdiomaMaterial =  (EditText) findViewById(R.id.add_idioma);
        final EditText editTextTipoMaterial =  (EditText) findViewById(R.id.add_tipo_mat);
        final TextView txtColeccion = (TextView) findViewById(R.id.add_coleccion);
        final TextView txtBiblioteca =  (TextView) findViewById(R.id.add_biblioteca);
        final Button btn_agregar=(Button)findViewById(R.id.btn_add_material);

        coleccionOnClick = new ColeccionOnClick();
        bibliotecaOnClick=new BibliotecanOnClick();

        txtBiblioteca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplication(), v);
                popupMenu.setOnMenuItemClickListener(bibliotecaOnClick);
                popupMenu.inflate(R.menu.menu_biblioteca);
                popupMenu.show();
            }
        });

        txtColeccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplication(), v);
                popupMenu.setOnMenuItemClickListener(coleccionOnClick);
                popupMenu.inflate(R.menu.menu_coleccion);
                popupMenu.show();
            }
        });
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTextTituloMaterial.getText().toString();
                String autor = editTextAutorMaterial.getText().toString();
                String idioma = editTextIdiomaMaterial.getText().toString();
                String tipo = editTextTipoMaterial.getText().toString();
                //String titulo = editTextTituloMaterial.getText().toString();
                String coleccion = txtColeccion.getText().toString();
                String biblioteca = txtBiblioteca.getText().toString();
            }
        });




    }

    private class  ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplication(), "Colección seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) findViewById(R.id.add_coleccion);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    private class  BibliotecanOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplication(), "Biblioteca seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) findViewById(R.id.add_biblioteca);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

}
