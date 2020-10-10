package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;

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

        Intent intent = getIntent();
        final String filtro = intent.getStringExtra(MESSAGE_KEY);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_material);
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readMaterial(new FireBaseDataBaseBiblitecaHelper.MaterialDataStatus(){
            @Override
            public void DataIsLoaded(List<Material> material, List<String> keys) {
                new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this, filtro);
            }
        }, filtro);
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