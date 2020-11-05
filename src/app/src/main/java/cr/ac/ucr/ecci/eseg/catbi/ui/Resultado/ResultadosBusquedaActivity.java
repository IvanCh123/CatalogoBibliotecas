package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class ResultadosBusquedaActivity extends AppCompatActivity implements RecyclerViewMaterial_Config.OnNoteListener{

    private RecyclerView mRecyclerView;

    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBibliotecaHelper;

    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";
    public final static String COLECCION_KEY ="coleccionKey";

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
        String palabraClave = intent.getStringExtra(MESSAGE_KEY).toLowerCase();
        String campoBusqueda = intent.getStringExtra(CAMPO_KEY).toLowerCase();
        String coleccion = intent.getStringExtra(COLECCION_KEY).toLowerCase();

        if(campoBusqueda.isEmpty())
            campoBusqueda = "todo";
        if(coleccion.isEmpty())
            coleccion = "general";

        palabraClave = StringUtils.stripAccents(palabraClave);
        coleccion = StringUtils.stripAccents(coleccion);

        return new String[]{palabraClave,campoBusqueda,coleccion};
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