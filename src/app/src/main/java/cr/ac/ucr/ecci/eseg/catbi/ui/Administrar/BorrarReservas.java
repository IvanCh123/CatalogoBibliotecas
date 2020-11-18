package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.RecyclerViewReservaciones_Config;

public class BorrarReservas extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private Context context;
    private Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_reservas);
        final Button seleccionar=(Button)findViewById(R.id.select_all);
        final Button deseleccionar=(Button)findViewById(R.id.deselect_all);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerViewEliminarReserva);
        context=getApplicationContext();
        activity=(Activity) context;


        new FireBaseDataBaseBiblitecaHelper().readReservas(new FireBaseDataBaseBiblitecaHelper.ReservaDataStatus() {
            @Override
            public void DataIsLoaded(List<Reservacion> reservacion, List<String> keys) {
                new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, context, reservacion, keys,activity);
            }
        }, "josue.valverde@ucr.ac.cr");

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarTodo();
            }
        });

        deseleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deseleccionarTodo();
            }
        });
    }

    private void seleccionarTodo(){

    }

    private void deseleccionarTodo(){

    }
}
