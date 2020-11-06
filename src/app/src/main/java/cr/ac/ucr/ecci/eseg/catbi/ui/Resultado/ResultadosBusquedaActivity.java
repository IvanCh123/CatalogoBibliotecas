package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.DataBaseHelperRoom;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.MaterialParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.RecyclerViewMaterial_Config;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class ResultadosBusquedaActivity extends AppCompatActivity implements RecyclerViewMaterial_Config.OnNoteListener{

    private RecyclerView mRecyclerView;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    private DataBaseHelperRoom dbLocalHelper;

    public final static String MESSAGE_KEY ="palabraKey";
    public final static String CAMPO_KEY ="campoBusquedaKey";
    public final static String COLECCION_KEY ="coleccionKey";

    private static final String TAG = "ResultadosBusquedaActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_busqueda);


        final String[] filtroFirebase = getFiltro();
        final String[] filtroBaseDatosLocal = getFiltroBaseDatosLocal();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_material);

        if(hayConexionAInternet()){
            mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
            mFireBaseDataBaseBiblitecaHelper.readMaterial(new FireBaseDataBaseBiblitecaHelper.MaterialDataStatus(){
                @Override
                public void DataIsLoaded(List<Material> material, List<String> keys) {

                    new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this, filtroFirebase, ResultadosBusquedaActivity.this);
                }
            }, filtroFirebase);

        }else{
            MaterialParametroAsyncTask parametroAsyncTask = new MaterialParametroAsyncTask(filtroBaseDatosLocal[2], filtroBaseDatosLocal[0], filtroBaseDatosLocal[1], new MaterialParametroAsyncTask.MaterialDataStatus() {
                @Override
                public void DataIsLoaded(List<Material> materiales) {
                    int tamanoLista = materiales.size();
                    List<String> keys = new ArrayList<>();

                    for(int i =0; i < tamanoLista; i++){
                        keys.add(String.valueOf(i));
                    }
                    new RecyclerViewMaterial_Config().setConfig(mRecyclerView,ResultadosBusquedaActivity.this,materiales,keys,ResultadosBusquedaActivity.this,filtroBaseDatosLocal,ResultadosBusquedaActivity.this);

                }
            });

            dbLocalHelper = new DataBaseHelperRoom(getApplicationContext());
            dbLocalHelper.readMaterialLocal(parametroAsyncTask);


        }

    }

    private String[] getFiltro() {
        Intent intent = getIntent();
        String palabraClave = intent.getStringExtra(MESSAGE_KEY).toLowerCase();
        String campoBusqueda = intent.getStringExtra(CAMPO_KEY).toLowerCase();
        String coleccion = intent.getStringExtra(COLECCION_KEY).toLowerCase();

        if(campoBusqueda.isEmpty())
            campoBusqueda = "todo";
        if(coleccion.isEmpty())
            coleccion = "todas";

        palabraClave = StringUtils.stripAccents(palabraClave);
        coleccion = StringUtils.stripAccents(coleccion);

        return new String[]{palabraClave,campoBusqueda,coleccion};
    }

    // Ver si se puede hacer un solo método
    private String [] getFiltroBaseDatosLocal(){
        Intent intent = getIntent();
        String palabraClave = intent.getStringExtra(MESSAGE_KEY);
        String campoBusqueda = intent.getStringExtra(CAMPO_KEY).toLowerCase();
        String coleccion = intent.getStringExtra(COLECCION_KEY);
        if(campoBusqueda.isEmpty())
            campoBusqueda = "todo";
        if(coleccion.isEmpty())
            coleccion = "todas";
        return new String[]{palabraClave,campoBusqueda,coleccion};
    }

    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked." + position);
        Intent intent = new Intent(getApplicationContext(), InformacionDetalladaMaterial.class);
        if(hayConexionAInternet()){
            Material materialClickeado = mFireBaseDataBaseBiblitecaHelper.getListaMaterial().get(position);
            intent.putExtra("materialClickeado", materialClickeado);
            startActivity(intent);
        }else{
            Material materialClickeado = dbLocalHelper.getMateriales().get(position);
            intent.putExtra("materialClickeado", materialClickeado);
            startActivity(intent);
        }


    }


}