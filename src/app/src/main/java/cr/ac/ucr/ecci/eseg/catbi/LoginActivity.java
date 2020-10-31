package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBibliotecaHelper;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correoUsuario;
    private EditText contrasenaUsuario;
    private Button btnInicioSesion;
    private ProgressBar barraProgreso;
    private FireBaseDataBaseBibliotecaHelper mFireBaseDataBaseBibliotecaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*
        //Se crea instancia del helper
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBibliotecaHelper();
        // Obtener datos de Firebase
        //Bibliotecas
        mFireBaseDataBaseBibliotecaHelper.readBibliotecas(new FireBaseDataBaseBibliotecaHelper.DataStatus(){
            @Override
            public void dataLoaded(List<Biblioteca> bibliotecas, List<String> keys) {
                insertarBibliotecasBaseLocal(bibliotecas);
            }
        });
        //Usuarios
        mFireBaseDataBaseBibliotecaHelper.readAllUsuarios(new FireBaseDataBaseBibliotecaHelper.UsuariosDataStatusList(){
            @Override
            public void DataIsLoaded(List<Usuario> usuarios, List<String> keys) {
                insertarUsuariosBaseLocal(usuarios);
            }
        });

        //Reservaciones
        mFireBaseDataBaseBibliotecaHelper.readAllReservaciones(new FireBaseDataBaseBibliotecaHelper.ReservaDataStatus(){
            @Override
            public void DataIsLoaded(List<Reservacion> reservaciones, List<String> keys) {
                insertarReservacionesBaseLocal(reservaciones);
            }
        });

        //Materiales
        mFireBaseDataBaseBibliotecaHelper.readAllMateriales(new FireBaseDataBaseBibliotecaHelper.MaterialDataStatus(){
            @Override
            public void DataIsLoaded(List<Material> materiales, List<String> keys) {
                insertarMaterialesBaseLocal(materiales);
            }
        });
        */
        mAuth = FirebaseAuth.getInstance();
        correoUsuario = findViewById(R.id.editTextCorreo);
        contrasenaUsuario = findViewById(R.id.editTextContrasena);
        btnInicioSesion = findViewById(R.id.btnInicioSesion);
        barraProgreso = findViewById(R.id.progressBar);
        barraProgreso.setVisibility(View.INVISIBLE);

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correoUsuario.getText().toString();
                String password = contrasenaUsuario.getText().toString();
                autenticarUsuarios(correo,password,view);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        barraProgreso.setVisibility(View.INVISIBLE);
    }


    public void autenticarUsuarios (String correo, String password, final View view){
        if (correo.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Por favor digite un correo y una contraseña",Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        barraProgreso.setVisibility(view.VISIBLE);
                    }else {
                        Toast.makeText(LoginActivity.this,"Credenciales inválidas",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void insertarBibliotecasBaseLocal(List<Biblioteca> bibliotecas){

    }

    public void insertarUsuariosBaseLocal(List<Usuario> usuarios){

    }

    public void insertarReservacionesBaseLocal(List<Reservacion> reservaciones){

    }

    public void insertarMaterialesBaseLocal(List<Material> material){

    }
}