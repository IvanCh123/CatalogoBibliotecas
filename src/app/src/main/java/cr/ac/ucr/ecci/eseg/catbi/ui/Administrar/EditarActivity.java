package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class EditarActivity extends AppCompatActivity {

    private BibliotecaOnClick bibliotecaOnClick;
    private ColeccionOnClick coleccionOnClick;
    private TextView _NOMBREMATERIAL,_BIBLIOTECA, _COLECCION, _CANTIDAD, _ID, _TITULO, _AUTOR, _IDIOMA, _FORMATO;
    private Material materialOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        bibliotecaOnClick = new BibliotecaOnClick();
        coleccionOnClick = new ColeccionOnClick();
        Material materialRecibido = (Material) getIntent().getSerializableExtra("materialClickeado");

        setMaterial(materialRecibido);
        materialOriginal = getNuevosDatos();

        _BIBLIOTECA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(bibliotecaOnClick);
                popupMenu.inflate(R.menu.menu_biblioteca);
                popupMenu.show();
            }
        });

        _COLECCION.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(coleccionOnClick);
                popupMenu.inflate(R.menu.menu_coleccion);
                popupMenu.show();
            }
        });
    }

    private void setMaterial(Material material) {
        _NOMBREMATERIAL = findViewById(R.id.textViewNombreMaterialEditor);
        _ID = findViewById(R.id.textViewContentIDEditor);
        _TITULO = findViewById(R.id.textViewContentTituloEditor);
        _AUTOR = findViewById(R.id.textViewContentAutorEditor);
        _IDIOMA = findViewById(R.id.textViewContentIdiomaEditor);
        _FORMATO = findViewById(R.id.textViewContentTipoEditor);
        _BIBLIOTECA = findViewById(R.id.textViewContentBibliotecaEditor);
        _COLECCION = findViewById(R.id.textViewContentColeccionEditor);
        _CANTIDAD = findViewById(R.id.textViewContentCantidadEditor);

        _NOMBREMATERIAL.setText(material.getTitulo());
        _ID.setText(material.getMaterialID());
        _TITULO.setText(material.getTitulo());
        _AUTOR.setText(material.getAutor());
        _IDIOMA.setText(material.getIdioma());
        _FORMATO.setText(material.getFormato());
        _BIBLIOTECA.setText(material.getBiblioteca());
        _COLECCION.setText(material.getColeccion());
        _CANTIDAD.setText(material.getCantidad());
    }

    private class BibliotecaOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            TextView txtCampoBusqueda = findViewById(R.id.textViewContentBibliotecaEditor);
            txtCampoBusqueda.setText(item.getTitle());

            return false;
        }
    }

    private class ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            TextView txtColeccion = findViewById(R.id.textViewContentColeccionEditor);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    public void actualizarDatos(View view){
        Material materialActualizado = getNuevosDatos();
        FireBaseDataBaseBiblitecaHelper db = new FireBaseDataBaseBiblitecaHelper();

        if(materialActualizado.isValid()){
            db.actualizarDatos(materialActualizado);
            Toast.makeText(getApplicationContext(), "Los datos se han actualizado",Toast.LENGTH_SHORT).show();
            DataBaseHelperRoom dbLocal = new DataBaseHelperRoom(getApplicationContext());
            // Se actualiza la base local
            dbLocal.actualizarMaterial(materialActualizado);
            actualizarMaterialRetorno(materialActualizado);

        }else{
            Toast.makeText(getApplicationContext(), "No deje datos vacios",Toast.LENGTH_SHORT).show();
        }

    }

    public void regresarADetalles(View view){
        actualizarMaterialRetorno(materialOriginal);
    }

    private void actualizarMaterialRetorno(Material materialActualizado) {
        Intent intent = new Intent(getApplicationContext(), InformacionDetalladaMaterial.class);
        intent.putExtra("materialActualizado", materialActualizado);
        setResult(RESULT_OK, intent);
        finish();
    }

    private Material getNuevosDatos()
    {
        Material materialActualizado = new Material();
        materialActualizado.setMaterialID(_ID.getText().toString());
        materialActualizado.setTitulo(_TITULO.getText().toString());
        materialActualizado.setAutor(_AUTOR.getText().toString());
        materialActualizado.setColeccion(_COLECCION.getText().toString());
        materialActualizado.setCantidad(_CANTIDAD.getText().toString());
        materialActualizado.setIdioma(_IDIOMA.getText().toString());
        materialActualizado.setFormato(_FORMATO.getText().toString());
        materialActualizado.setBiblioteca(_BIBLIOTECA.getText().toString());
        return materialActualizado;
    }
}