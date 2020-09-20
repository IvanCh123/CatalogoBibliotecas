package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultadosBusqueda extends AppCompatActivity {

    public final static String MESSAGE_KEY ="palabraKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);
        BottomNavigationView navView = (BottomNavigationView) findViewById(R.id.nav_view);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MESSAGE_KEY);
        TextView textView = (TextView) findViewById(R.id.txtPalabra);
        textView.setText(message);










    }


}