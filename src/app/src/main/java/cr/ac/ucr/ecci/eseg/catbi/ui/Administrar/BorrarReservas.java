package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.RecyclerViewReservaciones_Config;

public class BorrarReservas extends AppCompatActivity {
    private ListView mRecyclerView;
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    private String correo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_reservas);
        correo="josue.valverde@ucr.ac.cr";//Cambiar por lo que que viene de afuera
        final Button seleccionar=(Button)findViewById(R.id.select_all);
        final Button deseleccionar=(Button)findViewById(R.id.deselect_all);
        mRecyclerView=(ListView)findViewById(R.id.recyclerViewEliminarReserva);
        mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
        final List<ReservaFila> lista= new ArrayList<ReservaFila>();

        new FireBaseDataBaseBiblitecaHelper().readReservas(new FireBaseDataBaseBiblitecaHelper.ReservaDataStatus() {
            @Override
            public void DataIsLoaded(List<Reservacion> reservacion, List<String> keys) {
                for(int i=0;i<reservacion.size();i++){
                    lista.add(new ReservaFila(false,reservacion.get(i)));
                }
               // new RecyclerViewReservaciones_Config().setConfig(mRecyclerView, getApplicationContext(), reservacion, keys,this);
            }
        }, correo);


        /*lista.add(new ReservaFila("ALgo1","12",false,"1144"));
        lista.add(new ReservaFila("ALgo2","16",false,"134"));
        lista.add(new ReservaFila("ALgo3","15",false,"1678jhg44"));*/
        mRecyclerView.setAdapter(new AdapterReservaItem(this,lista));

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
