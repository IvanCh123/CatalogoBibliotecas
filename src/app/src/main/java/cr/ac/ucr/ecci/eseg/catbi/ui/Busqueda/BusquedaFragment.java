package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

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

import cr.ac.ucr.ecci.eseg.catbi.R;

public class BusquedaFragment extends Fragment {

    private BusquedaViewModel busquedaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        busquedaViewModel =
                ViewModelProviders.of(this).get(BusquedaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);
        final EditText editTextTituloFrase =  (EditText) root.findViewById(R.id.editTextTituloFrase);
        final Button btnBuscar =  (Button) root.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String palabra = editTextTituloFrase.getText().toString(); // Se captura lo que hay en el edit text
                realizarBusqueda(palabra,view);
            }
        });

        return root;
    }

    public void realizarBusqueda(String palabra, View view){
        // Por ahora solo se muestra en un toast
        if (palabra.isEmpty()){
            Toast.makeText(view.getContext(),"Digite lo que desea buscar por favor",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(view.getContext(),palabra,Toast.LENGTH_SHORT).show();
        }


    }
}