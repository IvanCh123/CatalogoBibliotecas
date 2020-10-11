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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView nombreUsuario;
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBibliotecaHelper;

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
        String userID = usuarioActual.getUid();
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus(){
            @Override
            public void DataIsLoaded(Usuarios usuarioP) {
                fillText(usuarioP);
            }
        },correo);
        return root;
    }

    public void fillText(Usuarios usuarioP){
        nombreUsuario.setText(usuarioP.getNombre());
        correoUsuario.setText(usuarioP.getCorreo());
    }

}