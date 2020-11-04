package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.AppDataBase;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBibliotecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.PerfilFragment;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correoUsuario;
    private EditText contrasenaUsuario;
    private Button btnInicioSesion;
    private ProgressBar barraProgreso;
    private FireBaseDataBaseBibliotecaHelper mFireBaseDataBaseBibliotecaHelper;
    private AppDataBase dbLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Se crea la base local
        dbLocal = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "bibliotecasStorage").fallbackToDestructiveMigration().build();

        //Se crea instancia del helper
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBibliotecaHelper();
        // Obtener datos de Firebase
        //Bibliotecas
        mFireBaseDataBaseBibliotecaHelper.readAllBibliotecas(new FireBaseDataBaseBibliotecaHelper.AllBibliotecasDataStatus() {
            @Override
            public void DataIsLoaded(List<Biblioteca> bibliotecas) {
                insertarBibliotecasBaseLocal(bibliotecas);
            }
        });
        //Usuarios
        mFireBaseDataBaseBibliotecaHelper.readAllUsuarios(new FireBaseDataBaseBibliotecaHelper.AllUsuariosDataStatus() {
            @Override
            public void DataIsLoaded(List<Usuario> usuarios) {
                insertarUsuariosBaseLocal(usuarios);
            }
        });

        //Reservaciones
        mFireBaseDataBaseBibliotecaHelper.readAllReservaciones(new FireBaseDataBaseBibliotecaHelper.AllReservacionesDataStatus() {
            @Override
            public void DataIsLoaded(List<Reservacion> reservaciones) {
                insertarReservacionesBaseLocal(reservaciones);
            }
        });

        //Materiales
        mFireBaseDataBaseBibliotecaHelper.readAllMateriales(new FireBaseDataBaseBibliotecaHelper.AllMaterialesDataStatus() {
            @Override
            public void DataIsLoaded(List<Material> materiales) {
                insertarMaterialesBaseLocal(materiales);
            }
        });

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
                //Para saber si tiene acceso a internet
                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(isConnected){
                    autenticarUsuariosFirebase(correo, password, view);
                }else{
                    autenticarUsuariosLocal(correo,password);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        barraProgreso.setVisibility(View.INVISIBLE);
    }


    public void autenticarUsuariosFirebase(String correo, String password, final View view) {
        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor digite un correo y una contraseña", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        barraProgreso.setVisibility(view.VISIBLE);
                    } else {
                        Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void autenticarUsuariosLocal(String correo, String password) {
        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor digite un correo y una contraseña", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario();
            new leerUsuario().execute(correo, password);
        }

    }

    public void insertarBibliotecasBaseLocal(List<Biblioteca> bibliotecas) {
        new insertarBibliotecas().execute(bibliotecas);
    }

    public void insertarUsuariosBaseLocal(List<Usuario> usuarios) {
        new insertarUsuarios().execute(usuarios);
    }

    public void insertarReservacionesBaseLocal(List<Reservacion> reservaciones) {
        new insertarReservaciones().execute(reservaciones);
    }

    public void insertarMaterialesBaseLocal(List<Material> materiales) {
        new insertarMateriales().execute(materiales);
    }

    private class insertarBibliotecas extends AsyncTask<List<Biblioteca>, Void, Void> {
        @Override
        protected Void doInBackground(List<Biblioteca>... lists) {
            List<Biblioteca> bibliotecas = lists[0];
            for (int i = 0; i < bibliotecas.size(); i++) {
                Biblioteca biblioteca = (Biblioteca) bibliotecas.get(i);
                dbLocal.bibliotecaDAO().insertar(biblioteca);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Se han insertado las bilbiotecas", Toast.LENGTH_LONG).show();
        }
    }

    private class insertarUsuarios extends AsyncTask<List<Usuario>, Void, Void> {
        @Override
        protected Void doInBackground(List<Usuario>... lists) {
            List<Usuario> usuarios = lists[0];
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = (Usuario) usuarios.get(i);
                dbLocal.usuarioDAO().insertar(usuario);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Se han insertado los usuarios", Toast.LENGTH_LONG).show();
        }
    }

    private class insertarReservaciones extends AsyncTask<List<Reservacion>, Void, Void> {
        @Override
        protected Void doInBackground(List<Reservacion>... lists) {
            List<Reservacion> reservaciones = lists[0];
            for (int i = 0; i < reservaciones.size(); i++) {
                Reservacion reservacion = (Reservacion) reservaciones.get(i);
                dbLocal.reservacionDAO().insertar(reservacion);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Se han insertado las reservaciones", Toast.LENGTH_LONG).show();
        }
    }

    private class insertarMateriales extends AsyncTask<List<Material>, Void, Void> {
        @Override
        protected Void doInBackground(List<Material>... lists) {
            List<Material> materiales = lists[0];
            for (int i = 0; i < materiales.size(); i++) {
                Material material = (Material) materiales.get(i);
                dbLocal.materialDAO().insertar(material);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Mostrar un mensaje para el usuario
            Toast.makeText(getApplicationContext(), "Se han insertado los materiales", Toast.LENGTH_LONG).show();
        }
    }

    private class leerUsuario extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... autenticacion) {
            String correo = autenticacion[0];
            String password = autenticacion[1];
            Usuario usuario = dbLocal.usuarioDAO().leerAutenticacion(correo, password);
            return usuario;
        }

        @Override
        protected void onPostExecute(Usuario usuario){
            if(usuario != null){

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("correoUsuarioActual", usuario.getCorreo());
                intent.putExtra("nombreUsuarioActual",usuario.getNombre());
                startActivity(intent);
                barraProgreso.setVisibility(View.VISIBLE);

            }else{
                Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}