package cr.ac.ucr.ecci.eseg.catbi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultadosBusqueda extends AppCompatActivity {

    public final static String MESSAGE_KEY ="palabraKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);
        TextView textView = new TextView(this);
        textView.setTextSize(45);
        textView.setText(message);
        setContentView(textView);



    }
}