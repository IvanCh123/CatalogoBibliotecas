package cr.ac.ucr.ecci.eseg.catbi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        
        correoUsuario =  (TextView) findViewById(R.id.txtNombre);
        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();
        correoUsuario.setText(usuarioActual.getEmail());
    }
}