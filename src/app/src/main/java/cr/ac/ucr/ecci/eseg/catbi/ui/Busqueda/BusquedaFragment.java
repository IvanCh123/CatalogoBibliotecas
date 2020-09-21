package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ResultadosBusqueda;

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel busquedaViewModel;
    public final static String MESSAGE_KEY ="palabraKey";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        busquedaViewModel =
                ViewModelProviders.of(this).get(BusquedaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        final EditText editTextTituloFrase =  (EditText) root.findViewById(R.id.editTextTituloFrase);
        final Button btnBuscar =  (Button) root.findViewById(R.id.btnBuscar);
        final Button btnLimpiar =  (Button) root.findViewById(R.id.btnLimpiar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabra = editTextTituloFrase.getText().toString(); // Se captura lo que hay en el edit text
                realizarBusqueda(palabra,view);
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarBusqueda(editTextTituloFrase);
            }
        });

        return root;
    }

    //  Historia CNQ - 4
    // Tarea ID CNQ -15
    // Gerald Bermúdez y Sebastián Otárola.
    public void realizarBusqueda(String palabra, View view){
        if (palabra.isEmpty()){
            Toast.makeText(view.getContext(),"Digite una palabra o frase",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent= new Intent(getContext(), ResultadosBusqueda.class); // Envío la palabra a buscar a la actividad de Iván.

            intent.putExtra(MESSAGE_KEY,palabra);

            startActivity(intent);

        }

    }

    public void limpiarBusqueda(EditText campoBusqueda){
        campoBusqueda.setText(""); // Limpio el campo de búsqueda

    }
}