package cr.ac.ucr.ecci.eseg.catbi.ui.Acceso;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cr.ac.ucr.ecci.eseg.catbi.Perfil;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class AccesoFragment extends Fragment {

    private AccesoViewModel accesoViewModel;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accesoViewModel =
                ViewModelProviders.of(this).get(AccesoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_acceso, container, false);
        final EditText correoUsuario = root.findViewById(R.id.editTextCorreo);
        final EditText contrasenaUsuario = root.findViewById(R.id.editTextContrasena);
        final Button btnInicioSesion = root.findViewById(R.id.btnInicioSesion);
        mAuth = FirebaseAuth.getInstance();

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correoUsuario.getText().toString();
                String password = contrasenaUsuario.getText().toString();
                autenticarUsuarios(correo,password,view);

            }
        });
        return root;
    }

    public void autenticarUsuarios (String correo, String password, View view){
        if (correo.isEmpty() || password.isEmpty()){
            Toast.makeText(view.getContext(),"Por favor digite un correo y una contraseña",Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(getContext(), Perfil.class));
                    }else {
                        Toast.makeText(getContext(),"Credenciales inválidas",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}