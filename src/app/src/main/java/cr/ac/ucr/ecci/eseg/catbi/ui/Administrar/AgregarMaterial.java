package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import cr.ac.ucr.ecci.eseg.catbi.ConfirmarAgregarMaterialDialogAlert;
import cr.ac.ucr.ecci.eseg.catbi.ConfirmarReservaDialogAlert;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RevervaLibros;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class AgregarMaterial extends AppCompatActivity {
    View v;
    private ColeccionOnClick coleccionOnClick;
    private BibliotecanOnClick bibliotecaOnClick;
    private boolean add =false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_material);
        final EditText editTextTituloMaterial =  (EditText) findViewById(R.id.add_titulo);
        final EditText editTextAutorMaterial =  (EditText) findViewById(R.id.add_autores);
        final EditText editTextCantMaterial =  (EditText) findViewById(R.id.add_cantidad);
        final EditText editTextAñotMaterial =  (EditText) findViewById(R.id.add_año);
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
                String cantidad = editTextCantMaterial.getText().toString();
                String coleccion = txtColeccion.getText().toString();
                String biblioteca = txtBiblioteca.getText().toString();
                String year=editTextAñotMaterial.getText().toString();
                boolean creado= crearMaterial(titulo,autor,idioma,tipo,cantidad,coleccion,biblioteca,year);
                if(creado){
                    retornar();
                }
            }
        });
    }

    private boolean crearMaterial(String titulo,String autor,String idioma,String tipo, String cant, String col, String biblio,String year){
        boolean matValido=true;
        boolean valido[];
        valido= new boolean[8];
        valido[0]=validarNumero(year,"Año");
        valido[1]=validarNumero(cant,"Cantidad");
        valido[2]=validarTexto(titulo,"Titulo");
        valido[3]=validarTexto(autor,"Autor");
        valido[4]=validarTexto(idioma,"Idioma");
        valido[5]=validarTexto(tipo,"Tipo");
        valido[6]=validarTexto(col,"Coleccion");
        valido[7]=validarTexto(biblio,"Biblioteca");
        for(int i=0;i<8;i++){
            if(false==valido[i]){
                matValido=false;
            }
        }
        if(matValido) {
            String[] mat={autor, year, cant, col, tipo, titulo, idioma, biblio};
            Material newMaterial=new Material(autor, year, cant, col, tipo, titulo, idioma, biblio);
            new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
            new FireBaseDataBaseBiblitecaHelper().addMaterial(newMaterial);
          //  newMaterial = new Material(autor, year, cant, col, tipo, titulo, idioma, biblio);
           /* DialogFragment confirmarDialogAlert=new ConfirmarAgregarMaterialDialogAlert();
            Bundle bundle = new Bundle();
            bundle.putStringArray("material",mat);
            confirmarDialogAlert.setArguments(bundle);
            confirmarDialogAlert.show(getSupportFragmentManager(),"Confirmar");*/
            //AgregarDialogFragment a = new AgregarDialogFragment();
            //a.show(getSupportFragmentManager(),"Confirmar");
            /*if(add){
                new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
                new FireBaseDataBaseBiblitecaHelper().addMaterial(newMaterial);
            }else{
                Toast.makeText(getApplication(), "Se ha cancelado el agregado de material" , Toast.LENGTH_SHORT).show();
            }*/

        }

        return true;
    }


    private boolean validarTexto(String s, String tipo){
        boolean result=true;
        if (s.isEmpty()){
            Toast.makeText(getApplication(),tipo+" es invalido",Toast.LENGTH_SHORT).show();
            result =false;
        }
        return result;
    }
    private boolean validarNumero(String n,String tipo){
        boolean result;
        try {
            Integer.parseInt(n);
            result=true;
        }catch (Exception e){
            Toast.makeText(getApplication(), tipo+" es invalida", Toast.LENGTH_SHORT).show();
            result=false;
        }
        return result;
    }

    private void retornar(){
        startActivity(new Intent(AgregarMaterial.this, MainActivity.class));
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
