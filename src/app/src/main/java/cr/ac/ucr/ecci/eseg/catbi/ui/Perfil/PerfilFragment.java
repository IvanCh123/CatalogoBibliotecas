package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.ReservacionParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Session;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.UsuarioParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.LoginActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.AgregarMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;
import cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones.Notificaciones;


import android.content.Context;
import android.widget.Toast;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView nombreUsuario;
    private TextView correoUsuario;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private DataBaseHelperRoom dbLocalHelper;
    private Session session;

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
        setHasOptionsMenu(true);
        nombreUsuario =  (TextView) root.findViewById(R.id.txtNombre);
        correoUsuario = (TextView) root.findViewById(R.id.txtCorreo);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.reservacionesRecyclerView);

        if(hayConexionAInternet()) {
            session = new Session(getContext());
            String correoUsuarioActual = session.getCorreo();
            mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
            mFireBaseDataBaseBiblitecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus() {
                @Override
                public void DataIsLoaded(Usuario usuarioP) {
                    fillText(usuarioP);
                }
            }, correoUsuarioActual);

            new FireBaseDataBaseBiblitecaHelper().readReservas(new FireBaseDataBaseBiblitecaHelper.ReservaDataStatus() {
                @Override
                public void DataIsLoaded(List<Reservacion> reservacion, List<String> keys) {
                    new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getContext(), reservacion, keys,getActivity());
                }
            }, correoUsuarioActual);
        }else{
            session = new Session(getContext());
            String correoUsuarioActual = session.getCorreo();
            dbLocalHelper = new DataBaseHelperRoom(getContext());

            // Para recuperar los datos del usuario
            UsuarioParametroAsyncTask usuarioParametroAsyncTask = new UsuarioParametroAsyncTask(correoUsuarioActual, new UsuarioParametroAsyncTask.UsuarioDataStatus() {
                @Override
                public void DataIsLoaded(final Usuario usuario) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fillText(usuario);
                        }
                    });
                }
            });
            dbLocalHelper.readUsuario(usuarioParametroAsyncTask);

            // Para recuperar las reservaciones
            ReservacionParametroAsyncTask parametroAsyncTask = new ReservacionParametroAsyncTask(correoUsuarioActual, new ReservacionParametroAsyncTask.ReservacionDataStatus() {
                @Override
                public void DataIsLoaded(List<Reservacion> reservaciones) {
                    int tamanoLista = reservaciones.size();
                    List<String> keys = new ArrayList<>();

                    for(int i =0; i < tamanoLista; i++){
                        keys.add(String.valueOf(i));
                    }
                    new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getContext(), reservaciones, keys,getActivity());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_perfil, menu);
        PerfilFragment.super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cerrar_sesion) {
            session.removeSession();
            session.removeAlarmaActiva(getContext());

            Intent intent= new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }else{
            Log.v("g","y");
        }
        return super.onOptionsItemSelected(item);
    }

}