package cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel ubicacionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ubicacionViewModel =
                ViewModelProviders.of(this).get(UbicacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubicacion, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        ubicacionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}