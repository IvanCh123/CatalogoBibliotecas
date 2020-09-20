package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemMenu = item.getItemId();
                Intent intentActPrincipal = new Intent(getBaseContext(), MainActivity.class);

                if(itemMenu == R.id.nav_busqueda){
                    Fragment mFragment = null;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    //fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

                }else if (itemMenu == R.id.nav_ubicacion){

                }else if (itemMenu == R.id.nav_preguntas){

                }
                return false;
            }
        });











    }


}