package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.MaterialParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.ReservacionParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBibliotecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

import android.content.Context;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView nombreUsuario;
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;
    private FireBaseDataBaseBibliotecaHelper mFireBaseDataBaseBibliotecaHelper;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private DataBaseHelperRoom dbLocalHelper;

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

        if(hayConexionAInternet()) {
            mAuth = FirebaseAuth.getInstance();
            usuarioActual = mAuth.getCurrentUser();
            String correo = usuarioActual.getEmail();
            String userID = usuarioActual.getUid();
            mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBibliotecaHelper();
            mFireBaseDataBaseBibliotecaHelper.readUsuarios(new FireBaseDataBaseBibliotecaHelper.UsuariosDataStatus() {
                @Override
                public void DataIsLoaded(Usuario usuarioP) {
                    fillText(usuarioP);
                }
            }, correo);
            mRecyclerView = (RecyclerView) root.findViewById(R.id.reservacionesRecyclerView);
            new FireBaseDataBaseBibliotecaHelper().readReservas(new FireBaseDataBaseBibliotecaHelper.ReservaDataStatus() {
                @Override
                public void DataIsLoaded(List<Reservacion> reservacion, List<String> keys) {
                    new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getContext(), reservacion, keys);
                }
            }, correo);
        }else{
            String correoUsuarioActual = getArguments().getString("correoUsuarioActual");
            String nombreUsuarioActual = getArguments().getString("nombreUsuarioActual");
            nombreUsuario.setText(nombreUsuarioActual);
            correoUsuario.setText(correoUsuarioActual);
            dbLocalHelper = new DataBaseHelperRoom(getContext());

            ReservacionParametroAsyncTask parametroAsyncTask = new ReservacionParametroAsyncTask(correoUsuarioActual, new ReservacionParametroAsyncTask.ReservacionDataStatus() {
                @Override
                public void DataIsLoaded(List<Reservacion> reservaciones) {
                    int tamanoLista = reservaciones.size();
                    List<String> keys = new ArrayList<>();

                    for(int i =0; i < tamanoLista; i++){
                        keys.add(String.valueOf(i));
                    }
                    new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getContext(), reservaciones, keys);
                }
            });

            dbLocalHelper.readReservacion(parametroAsyncTask);
        }
        return root;
    }

    public void fillText(Usuario usuarioP){
        nombreUsuario.setText(usuarioP.getNombre());
        correoUsuario.setText(usuarioP.getCorreo());
    }

    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}