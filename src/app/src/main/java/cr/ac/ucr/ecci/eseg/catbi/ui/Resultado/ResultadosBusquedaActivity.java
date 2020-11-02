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
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.ObjetoParametroAsyncTask;
import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBibliotecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;

public class ResultadosBusquedaActivity extends AppCompatActivity implements RecyclerViewMaterial_Config.OnNoteListener{

    private RecyclerView mRecyclerView;
    private FireBaseDataBaseBibliotecaHelper mFireBaseDataBaseBibliotecaHelper;
    private DataBaseHelperRoom dbLocalHelper;

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

        //Para saber si tiene acceso a internet
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBibliotecaHelper();
            mFireBaseDataBaseBibliotecaHelper.readMaterial(new FireBaseDataBaseBibliotecaHelper.MaterialDataStatus(){
                @Override
                public void DataIsLoaded(List<Material> material, List<String> keys) {

                    new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this, filtro, ResultadosBusquedaActivity.this);
                }
            }, filtro);

        }else{
            ObjetoParametroAsyncTask parametroAsyncTask = new ObjetoParametroAsyncTask(filtro[2], filtro[0], filtro[1], new ObjetoParametroAsyncTask.MaterialDataStatus() {
                @Override
                public void DataIsLoaded(List<Material> materiales) {
                    int tamanoLista = materiales.size();
                    List<String> keys = new ArrayList<>();

                    for(int i =0; i < tamanoLista; i++){
                        keys.add(String.valueOf(i));
                    }
                    new RecyclerViewMaterial_Config().setConfig(mRecyclerView,ResultadosBusquedaActivity.this,materiales,keys,ResultadosBusquedaActivity.this,filtro,ResultadosBusquedaActivity.this);

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