package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.InformacionDetalladaMaterial;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.RecyclerViewMaterial_Config;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.ConfirmarReservaDialogAlert;

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
        final boolean[] detalle = {false};

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_material);
        mFireBaseDataBaseBibliotecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBibliotecaHelper.readMaterial(new FireBaseDataBaseBiblitecaHelper.MaterialDataStatus(){
            @Override
            public void DataIsLoaded(List<Material> material, List<String> keys) {
                detalle[0] =true;
                new RecyclerViewMaterial_Config().setConfig(mRecyclerView, ResultadosBusquedaActivity.this,material,keys,ResultadosBusquedaActivity.this, filtro);
            }
        }, filtro);

        //
        if(detalle[0] == true){
            final Button btnReserva= (Button) findViewById(R.id.button_reserva);
            btnReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("button","1");
                    confirmarReserva();
                }
            });
        }


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

    public void confirmarReserva(){
        ConfirmarReservaDialogAlert confirmarReservaDialogAlert=new ConfirmarReservaDialogAlert();
        confirmarReservaDialogAlert.show(getSupportFragmentManager(),"Confirmar");
    }
}