package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import android.content.Context;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Session;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.UsuarioParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.AgregarMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.BuscarUsuarios;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

public class BusquedaFragment extends Fragment {

    private CampoBusquedaOnClick campoBusquedaOnClick;
    private ColeccionOnClick coleccionOnClick;
    private BusquedaViewModel busquedaViewModel;
    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";
    public final static String COLECCION_KEY ="coleccionKey";
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    private DataBaseHelperRoom dbLocalHelper;
    private Session session;
    private View root = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        busquedaViewModel =
                ViewModelProviders.of(this).get(BusquedaViewModel.class);
        root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        setHasOptionsMenu(true);

        session = new Session(getContext());
        final EditText editTextTituloFrase =  (EditText) root.findViewById(R.id.editTextTituloFrase);
        final TextView txtCampoBusqueda = (TextView) root.findViewById(R.id.menuBusqueda_textView);
        final TextView txtColeccion = (TextView) root.findViewById(R.id.menuColeccion_textView);
        final Button btnBuscar =  (Button) root.findViewById(R.id.btnBuscar);
        final Button btnLimpiar =  (Button) root.findViewById(R.id.btnLimpiar);

        campoBusquedaOnClick = new CampoBusquedaOnClick();
        coleccionOnClick = new ColeccionOnClick();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabra = editTextTituloFrase.getText().toString(); // Se captura lo que hay en el edit text
                String campo = txtCampoBusqueda.getText().toString();
                String coleccion = txtColeccion.getText().toString();

                realizarBusqueda(palabra,campo,coleccion,view);
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarBusqueda(editTextTituloFrase);
            }
        });

        txtCampoBusqueda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                popupMenu.setOnMenuItemClickListener(campoBusquedaOnClick);
                popupMenu.inflate(R.menu.campo_busqueda);
                popupMenu.show();
            }
        });

        txtColeccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                popupMenu.setOnMenuItemClickListener(coleccionOnClick);
                popupMenu.inflate(R.menu.menu_coleccion);
                popupMenu.show();
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        String correoUsuarioActual = session.getCorreo();
        if (hayConexionAInternet()){
            mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
            mFireBaseDataBaseBiblitecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus() {
                @Override
                public void DataIsLoaded(Usuario usuarioP) {
                    // Ac?? deber??a de ir a la l??gica de comprobaci??n de si el usuario es administrador o no.
                    if(usuarioP.getRango().equals("administrador")) {
                        inflater.inflate(R.menu.add_material, menu);
                        BusquedaFragment.super.onCreateOptionsMenu(menu, inflater);
                    }
                }
            }, correoUsuarioActual);
        }else {
            dbLocalHelper = new DataBaseHelperRoom(getContext());
            // Para recuperar los datos del usuario
            UsuarioParametroAsyncTask usuarioParametroAsyncTask = new UsuarioParametroAsyncTask(correoUsuarioActual, new UsuarioParametroAsyncTask.UsuarioDataStatus() {
                @Override
                public void DataIsLoaded(final Usuario usuario) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(usuario.getRango().equals("administrador")) {
                                inflater.inflate(R.menu.add_material, menu);
                                BusquedaFragment.super.onCreateOptionsMenu(menu, inflater);
                            }
                        }
                    });
                }
            });
            dbLocalHelper.readUsuario(usuarioParametroAsyncTask);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.formulario_agregar) {
            if (hayConexionAInternet()){
                irActAgregarMat();
            }else{
                Toast.makeText(getContext(), "El dispositivo no tiene conexi??n a internet, no se permite agregar material.",Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if (id == R.id.buscar_usuario) {
            if (hayConexionAInternet()){
                Log.v("gooooool","e");
                irActBuscUser();
            }else{
                Toast.makeText(getContext(), "El dispositivo no tiene conexi??n a internet, no se permite agregar material.",Toast.LENGTH_LONG).show();
            }
            return true;
        }else{
            Log.v("g","y");
        }
        return super.onOptionsItemSelected(item);
    }

    private void irActAgregarMat(){
        Intent intent= new Intent(getActivity(), AgregarMaterial.class);
        startActivity(intent);
    }

    private void irActBuscUser(){
        Intent intent= new Intent(getActivity(), BuscarUsuarios.class);
        startActivity(intent);
    }

    private class  CampoBusquedaOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getContext(), "Campo de b??squeda seleccionado: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtCampoBusqueda = (TextView) root.findViewById(R.id.menuBusqueda_textView);
            txtCampoBusqueda.setText(item.getTitle());

            return false;
        }
    }

    private class ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getContext(), "Colecci??n seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) root.findViewById(R.id.menuColeccion_textView);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    //  Historia CNQ - 4
    // Tarea ID CNQ -15
    // Gerald Berm??dez y Sebasti??n Ot??rola.
    public void realizarBusqueda(String palabra,String campo, String coleccion, View view){
        if (palabra.isEmpty()){
            Toast.makeText(view.getContext(),"Digite una palabra o frase",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent= new Intent(getContext(), ResultadosBusquedaActivity.class); // Env??o la palabra a buscar a la actividad de Iv??n.
            intent.putExtra(CAMPO_KEY,campo);
            intent.putExtra(MESSAGE_KEY,palabra);
            intent.putExtra(COLECCION_KEY,coleccion);

            startActivity(intent);
        }
    }

    public void limpiarBusqueda(EditText campoBusqueda){
        campoBusqueda.setText(""); // Limpio el campo de b??squeda
    }
    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}