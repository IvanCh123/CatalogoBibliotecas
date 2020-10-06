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

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText correoUsuario;
    private EditText contrasenaUsuario;
    private Button btnInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        correoUsuario = findViewById(R.id.editTextCorreo);
        contrasenaUsuario = findViewById(R.id.editTextContrasena);
        btnInicioSesion = findViewById(R.id.btnInicioSesion);
        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correoUsuario.getText().toString();
                String password = contrasenaUsuario.getText().toString();
                autenticarUsuarios(correo,password,view);

            }
        });
    }

    public void autenticarUsuarios (String correo, String password, View view){
        if (correo.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this,"Por favor digite un correo y una contraseña",Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this,"Credenciales inválidas",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}