package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas.RecycleViewBibliotecaConfig;

public class ListarUsuarios extends AppCompatActivity {
    public final static String USER_NAME ="nombreUser";
    private String nombre;
    private RecyclerView mRecyclerView;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    Context context;
    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        nombre=getFiltro();
        mRecyclerView=(RecyclerView)findViewById(R.id.lista_user);
        context=getApplicationContext();

        new FireBaseDataBaseBiblitecaHelper().searchUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosStatus() {

            @Override
            public void DataIsLoaded(List<Usuario> listaUser, List<String> keys) {
                //Log.d("Tq", String.valueOf(listaBibliotecas.size()));
                new RecycleViewUserConfig().setConfig(mRecyclerView,context , listaUser, keys,ListarUsuarios.this);
            }
        },nombre);

        /*mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper(new FireBaseDataBaseBiblitecaHelper().readUsuariosLista(){

        },nombre);*/

    }

    private String getFiltro() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra(USER_NAME).toLowerCase();
        userName = StringUtils.stripAccents(userName);
        return userName;
    }

}
