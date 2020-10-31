package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;

import android.content.Context;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView nombreUsuario;
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBibliotecaHelper;
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombreUsuario =  (TextView) root.findViewById(R.id.txtNombre);
        correoUsuario = (TextView) root.findViewById(R.id.txtCorreo);
        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();
        String correo = usuarioActual.getEmail();
        String userID = usuarioActual.getUid();
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus(){
            @Override
            public void DataIsLoaded(Usuario usuarioP) {
                fillText(usuarioP);
            }
        },correo);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.reservacionesRecyclerView);
        new FireBaseDataBaseBiblitecaHelper().readReservas(new FireBaseDataBaseBiblitecaHelper.ReservaDataStatus(){
            @Override
            public void DataIsLoaded(List<Reservacion> reservacion, List<String> keys){
                new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getContext(), reservacion, keys);
            }
        }, correo);
        return root;
    }

    public void fillText(Usuario usuarioP){
        nombreUsuario.setText(usuarioP.getNombre());
        correoUsuario.setText(usuarioP.getCorreo());
    }

}