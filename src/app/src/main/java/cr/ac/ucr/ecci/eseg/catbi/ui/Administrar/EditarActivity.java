package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class EditarActivity extends AppCompatActivity {

    private BibliotecaOnClick bibliotecaOnClick;
    private ColeccionOnClick coleccionOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        bibliotecaOnClick = new BibliotecaOnClick();
        coleccionOnClick = new ColeccionOnClick();
        final Material materialRecibido = (Material) getIntent().getSerializableExtra("materialClickeado");
        TextView biblioteca = findViewById(R.id.textViewContentBibliotecaEditor);
        TextView coleccion = findViewById(R.id.textViewContentColeccionEditor);

        setMaterial(biblioteca, coleccion, materialRecibido);

        biblioteca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(bibliotecaOnClick);
                popupMenu.inflate(R.menu.menu_biblioteca);
                popupMenu.show();
            }
        });

        coleccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(coleccionOnClick);
                popupMenu.inflate(R.menu.menu_coleccion);
                popupMenu.show();
            }
        });
    }

    private void setMaterial(TextView biblioteca, TextView coleccion, Material material) {
        TextView nombreMaterial = findViewById(R.id.textViewNombreMaterialEditor);
        TextView id = findViewById(R.id.textViewContentIDEditor);
        TextView titulo = findViewById(R.id.textViewContentTituloEditor);
        TextView autor = findViewById(R.id.textViewContentAutorEditor);
        TextView idioma = findViewById(R.id.textViewContentIdiomaEditor);
        TextView tipo = findViewById(R.id.textViewContentTipoEditor);

        nombreMaterial.setText(material.getTitulo());
        id.setText(material.getID());
        titulo.setText(material.getTitulo());
        autor.setText(material.getAutor());
        idioma.setText(material.getIdioma());
        tipo.setText(material.getFormato());
        biblioteca.setText(material.getBiblioteca());
        coleccion.setText(material.getColeccion());

    }

    private class BibliotecaOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplicationContext(), "Campo de búsqueda seleccionado: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtCampoBusqueda = findViewById(R.id.textViewContentBibliotecaEditor);
            txtCampoBusqueda.setText(item.getTitle());

            return false;
        }
    }

    private class ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplicationContext(), "Colección seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = findViewById(R.id.textViewContentColeccionEditor);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }


}