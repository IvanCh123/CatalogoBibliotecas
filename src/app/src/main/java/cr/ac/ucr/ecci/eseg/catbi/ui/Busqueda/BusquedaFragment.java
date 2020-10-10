package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.ResultadosBusquedaActivity;

public class BusquedaFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private BusquedaViewModel busquedaViewModel;
    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";
    private View root = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        busquedaViewModel =
                ViewModelProviders.of(this).get(BusquedaViewModel.class);
        root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        final EditText editTextTituloFrase =  (EditText) root.findViewById(R.id.editTextTituloFrase);
        final TextView txtCampoBusqueda = (TextView) root.findViewById(R.id.menuBusqueda_textView);
        final Button btnBuscar =  (Button) root.findViewById(R.id.btnBuscar);
        final Button btnLimpiar =  (Button) root.findViewById(R.id.btnLimpiar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabra = editTextTituloFrase.getText().toString(); // Se captura lo que hay en el edit text
                String campo = txtCampoBusqueda.getText().toString();

                realizarBusqueda(palabra,campo,view);
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
                popupMenu.setOnMenuItemClickListener(BusquedaFragment.this);
                popupMenu.inflate(R.menu.campo_busqueda);
                popupMenu.show();
            }
        });

        return root;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(getContext(), "Campo de búsqueda seleccionado: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        TextView txtCampoBusqueda = (TextView) root.findViewById(R.id.menuBusqueda_textView);
        txtCampoBusqueda.setText(item.getTitle());

        return false;
    }//

    //  Historia CNQ - 4
    // Tarea ID CNQ -15
    // Gerald Bermúdez y Sebastián Otárola.
    public void realizarBusqueda(String palabra,String campo, View view){
        if (palabra.isEmpty()){
            Toast.makeText(view.getContext(),"Digite una palabra o frase",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent= new Intent(getContext(), ResultadosBusquedaActivity.class); // Envío la palabra a buscar a la actividad de Iván.
            intent.putExtra(CAMPO_KEY,campo);
            intent.putExtra(MESSAGE_KEY,palabra);

            startActivity(intent);
        }
    }

    public void limpiarBusqueda(EditText campoBusqueda){
        campoBusqueda.setText(""); // Limpio el campo de búsqueda
    }
}