package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

public class BuscarUsuarios extends AppCompatActivity {
    public final static String USER_NAME ="nombreUser";
    String usuario ="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_usuario);
        final Button btn_buscar=(Button)findViewById(R.id.usuario_buscar);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario="@@@";
                realizarBusquedaUsuario(usuario,v);
            }
        });
    }

    public void realizarBusquedaUsuario(String usuario, View view){
        if (usuario.isEmpty()){
            Toast.makeText(view.getContext(),"Digite un nombre de usuario",Toast.LENGTH_SHORT).show();
        }else{
           /* Intent intent= new Intent(getApplicationContext(), UserBusquedaActivity.class); // Envío la palabra a buscar a la actividad de Iván.
            intent.putExtra(USER_NAME,usuario);
            startActivity(intent);*/
        }
    }
}
