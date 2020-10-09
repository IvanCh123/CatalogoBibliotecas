package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView nombreUsuario;
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombreUsuario =  (TextView) root.findViewById(R.id.txtNombre);
        correoUsuario = (TextView) root.findViewById(R.id.txtCorreo);
        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();
        String correo = usuarioActual.getEmail();

        nombreUsuario.setText(separarNombreUsuario(correo));
        correoUsuario.setText(correo);

        return root;
    }

    public String separarNombreUsuario(String correo){
        String nombreUsuario = "";
        if (correo != null) {
            int posArroba = correo.indexOf("@");
            nombreUsuario = correo.substring(0, posArroba);
        }
        return nombreUsuario;

    }


}