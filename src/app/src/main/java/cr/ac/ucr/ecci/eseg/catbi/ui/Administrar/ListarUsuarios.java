package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.StringUtils;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class ListarUsuarios extends AppCompatActivity {
    public final static String USER_NAME ="nombreUser";
    private String nombre;
    private RecyclerView mRecyclerView;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_usuario);
        nombre=getFiltro();

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
