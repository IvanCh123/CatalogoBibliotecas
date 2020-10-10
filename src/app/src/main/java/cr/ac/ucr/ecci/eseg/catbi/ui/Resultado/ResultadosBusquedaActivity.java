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

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;

public class ResultadosBusquedaActivity extends AppCompatActivity implements RecyclerViewMaterial_Config.OnNoteListener{

    private RecyclerView mRecyclerView;

    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBibliotecaHelper;

    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";

    private static final String TAG = "ResultadosBusquedaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);

        final String[] filtro = getFiltro();


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_material);
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readMaterial(new FireBaseDataBaseBiblitecaHelper.MaterialDataStatus(){
            @Override
            public void DataIsLoaded(List<Material> material, List<String> keys) {
                new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this, filtro);
            }
        }, filtro);

    }

    private String[] getFiltro() {
        Intent intent = getIntent();
        String palabraClave = intent.getStringExtra(MESSAGE_KEY);
        String campoBusqueda = intent.getStringExtra(CAMPO_KEY).toLowerCase();

        palabraClave = StringUtils.stripAccents(palabraClave).toLowerCase();

        String[] filtro = {};
        if(!campoBusqueda.isEmpty()){
            filtro = new String[]{palabraClave, campoBusqueda};
        }else{
            filtro = new String[]{palabraClave,"todo"};
        }
        return filtro;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked." + position);
        Intent intent = new Intent(getApplicationContext(), InformacionDetalladaMaterial.class);
        Material materialClickeado = mFireBaseDataBaseBibliotecaHelper.getListaMaterial().get(position);
        intent.putExtra("materialClickeado", materialClickeado);
        startActivity(intent);
    }
}