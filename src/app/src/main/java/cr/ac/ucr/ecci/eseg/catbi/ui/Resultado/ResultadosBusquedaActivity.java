package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;

public class ResultadosBusquedaActivity extends AppCompatActivity implements RecyclerViewMaterial_Config.OnNoteListener{

    private RecyclerView mRecyclerView;

    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBibliotecaHelper;

    public final static String MESSAGE_KEY ="palabraKey";

    private static final String TAG = "ResultadosBusquedaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_material);
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readMaterial(new FireBaseDataBaseBiblitecaHelper.MaterialDataStatus(){
            @Override
            public void DataIsLoaded(List<Material> material, List<String> keys) {
                new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this);
            }
        });

        /*Con esto se obtiene la palabra escrita en el buscador*/
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MESSAGE_KEY);
//        TextView textView = new TextView(this);
//        textView.setTextSize(45);
//        textView.setText("Mostrando resultados para:" +message);
//        setContentView(textView);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNoteClick(int position) {
        //Y un get position para obtener el objeto en el arreglo que debo, que seria algo como:
        //mFireBaseDataBaseBibliotecaHelper.getListaMaterial().get(position);
        Log.d(TAG, "onNoteClick: clicked." + position);
        //Aqui iria el intent
        //Intent intent = new Intent(this, InformacionDetalladaMaterial.class);
        //intent.putExtra();
        //startActivity(intent);
    }
}