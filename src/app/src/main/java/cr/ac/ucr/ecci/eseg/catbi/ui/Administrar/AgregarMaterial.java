package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import cr.ac.ucr.ecci.eseg.catbi.ui.Alert.ConfirmarAgregarMaterialDialogAlert;
import cr.ac.ucr.ecci.eseg.catbi.R;

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
        //Funcion que despliega opciones de biblioteca
        txtBiblioteca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplication(), v);
                popupMenu.setOnMenuItemClickListener(bibliotecaOnClick);
                popupMenu.inflate(R.menu.menu_biblioteca);
                popupMenu.show();
            }
        });
        //Funcion que despliega opciones de colecciones
        txtColeccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplication(), v);
                popupMenu.setOnMenuItemClickListener(coleccionOnClick);
                popupMenu.inflate(R.menu.menu_formatos);
                popupMenu.show();
            }
        });
        //Funcion que realiza operacion de agregado d material
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
                /*if(creado){
                    Toast.makeText(getApplication(), "Se a agregado el material a la base de datos ", Toast.LENGTH_SHORT).show();
                    retornar();
                }else{
                    Toast.makeText(getApplication(), "Se a presentado un error al agregar datos a la base de datos ", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    //Funcion que reune los datos y llama a confirmar el agregado de datos
    private boolean crearMaterial(String titulo,String autor,String idioma,String tipo, String cant, String col, String biblio,String year){
        boolean matValido=true;
        boolean valido[];
        boolean agregado=false;
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
            String[] materialInfo={autor, year, cant, col, tipo, titulo, idioma, biblio};
            DialogFragment confirmarDialogAlert=new ConfirmarAgregarMaterialDialogAlert();
            Bundle bundle = new Bundle();
            bundle.putStringArray("material",materialInfo);
            confirmarDialogAlert.setArguments(bundle);
            confirmarDialogAlert.show(getSupportFragmentManager(),"Confirmar");
            //Material newMaterial=new Material(autor, year, cant, col, tipo, titulo, idioma, biblio);
            // new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
            //agregado=new FireBaseDataBaseBiblitecaHelper().addMaterial(newMaterial);
            //  newMaterial = new Material(autor, year, cant, col, tipo, titulo, idioma, biblio);
            //AgregarDialogFragment a = new AgregarDialogFragment();
            //a.show(getSupportFragmentManager(),"Confirmar");
            /*if(add){
                new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
                new FireBaseDataBaseBiblitecaHelper().addMaterial(newMaterial);
            }else{
                Toast.makeText(getApplication(), "Se ha cancelado el agregado de material" , Toast.LENGTH_SHORT).show();
            }*/

        }

        return agregado;
    }

    // funcion que valida que los campos de texto no esten vacios
    private boolean validarTexto(String s, String tipo){
        boolean result=true;
        if (s.isEmpty()){
            Toast.makeText(getApplication(),tipo+" es invalido",Toast.LENGTH_SHORT).show();
            result =false;
        }
        return result;
    }

    // funcion que valida que los campos numericos no esten vacios y sean de numeros
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

    // Funcion que carga opciones de la lista de colecciones
    private class ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplication(), "Colección seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) findViewById(R.id.add_coleccion);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    // Funcion que carga opciones de la lista de bibliotecas
    private class  BibliotecanOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getApplication(), "Biblioteca seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) findViewById(R.id.add_biblioteca);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    /*
    private void retornar(){
        startActivity(new Intent(AgregarMaterial.this, MainActivity.class));
    }*/
}
