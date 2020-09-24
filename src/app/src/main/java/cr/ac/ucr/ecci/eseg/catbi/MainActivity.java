package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(  // Si quitamos esta linea evitamos que se muestre cada uno de los títulos.
                R.id.nav_busqueda, R.id.nav_ubicacion, R.id.nav_preguntas)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        /*NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/
        NavigationUI.setupWithNavController(navView, navController);

    }

   // @Override
   /* public void goToAnActivity(View view) {
        String name="ALGO";
        double L1=9.912440;
        double L2=-84.073091;
        switch (view.getId()){
            case R.id.Prueba:
                Intent intent1 = new Intent(this,BibliotecaUbicacion.class);
                intent1.putExtra("name", name);
                intent1.putExtra("L1", L1);
                intent1.putExtra("L2", L2);
                startActivity(intent1);
                break;
           /* case R.id.button2:
                Intent intent2 = new Intent(this, AnotherActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
    /*public void goToAnActivity(View view) {
        Intent intent = new Intent(this, BibliotecaUbicacion.class);
        startActivity(intent);
    }*/



}