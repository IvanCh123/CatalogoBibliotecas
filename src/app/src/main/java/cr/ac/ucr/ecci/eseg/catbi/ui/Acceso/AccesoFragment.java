package cr.ac.ucr.ecci.eseg.catbi.ui.Acceso;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cr.ac.ucr.ecci.eseg.catbi.Perfil;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class AccesoFragment extends Fragment {

    private AccesoViewModel accesoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accesoViewModel =
                ViewModelProviders.of(this).get(AccesoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_acceso, container, false);

        return root;
    }


}