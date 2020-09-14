package cr.ac.ucr.ecci.eseg.catbi.ui.Preguntas;

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

public class PreguntasFragment extends Fragment {

    private PreguntasViewModel preguntasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        preguntasViewModel =
                ViewModelProviders.of(this).get(PreguntasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_preguntas, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        preguntasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}