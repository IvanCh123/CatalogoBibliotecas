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

import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.AgregarMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

public class BusquedaFragment extends Fragment {

    private CampoBusquedaOnClick campoBusquedaOnClick;
    private ColeccionOnClick coleccionOnClick;
    private BusquedaViewModel busquedaViewModel;
    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";
    public final static String COLECCION_KEY ="coleccionKey";
    private View root = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        busquedaViewModel =
                ViewModelProviders.of(this).get(BusquedaViewModel.class);
        root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        setHasOptionsMenu(true);


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
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Acá debería de ir a la lógica de comprobación de si el usuario es administrador o no.
        //inflater.inflate(R.menu.add_material, menu);
       // super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.formulario_agregar) {
            if (hayConexionAInternet()){
                irActAgregarMat();
            }else{
                Toast.makeText(getContext(), "El dispositivo no tiene conexión a internet, no se permite agregar material.",Toast.LENGTH_LONG).show();
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

    private class  CampoBusquedaOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getContext(), "Campo de búsqueda seleccionado: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtCampoBusqueda = (TextView) root.findViewById(R.id.menuBusqueda_textView);
            txtCampoBusqueda.setText(item.getTitle());

            return false;
        }
    }

    private class ColeccionOnClick implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(getContext(), "Colección seleccionada: " +item.getTitle(), Toast.LENGTH_SHORT).show();

            TextView txtColeccion = (TextView) root.findViewById(R.id.menuColeccion_textView);
            txtColeccion.setText(item.getTitle());

            return false;
        }
    }

    //  Historia CNQ - 4
    // Tarea ID CNQ -15
    // Gerald Bermúdez y Sebastián Otárola.
    public void realizarBusqueda(String palabra,String campo, String coleccion, View view){
        if (palabra.isEmpty()){
            Toast.makeText(view.getContext(),"Digite una palabra o frase",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent= new Intent(getContext(), ResultadosBusquedaActivity.class); // Envío la palabra a buscar a la actividad de Iván.
            intent.putExtra(CAMPO_KEY,campo);
            intent.putExtra(MESSAGE_KEY,palabra);
            intent.putExtra(COLECCION_KEY,coleccion);

            startActivity(intent);
        }
    }

    public void limpiarBusqueda(EditText campoBusqueda){
        campoBusqueda.setText(""); // Limpio el campo de búsqueda
    }
    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}