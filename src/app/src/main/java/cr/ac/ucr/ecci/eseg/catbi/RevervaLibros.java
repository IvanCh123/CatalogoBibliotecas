package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RevervaLibros extends AppCompatActivity {
    private String biblio;
    private String titulo;
    private String id;
    private String user;
    private String cant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        //nombre=intent.getStringExtra("name");
       // l1=intent.getDoubleExtra("L1",90);
       // l2=intent.getDoubleExtra("L2",90);
        biblio=intent.getStringExtra("biblio");
        titulo=intent.getStringExtra("titulo");
        id=intent.getStringExtra("id");
        user=intent.getStringExtra("user");
        cant=intent.getStringExtra("cant");

        boolean v=validadorPrestamo(cant);
        if(v){
            setContentView(R.layout.reserva_posible);
            TextView textViewNombreLibro=findViewById(R.id.nombre_libro);
            TextView textViewNombreBiblio=findViewById(R.id.nombre_biblioteca);
            TextView textViewNombreDuracion=findViewById(R.id.duracion_reserva);
            textViewNombreLibro.setText(titulo);
            textViewNombreBiblio.setText(biblio);
            textViewNombreDuracion.setText("15 dias");

            final Button btnReserva= (Button) findViewById(R.id.buttonAccpReser);
            btnReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     agregarReserva();
                }
            });
        }else{
            setContentView(R.layout.reserva_no_posible);
            TextView textViewNombreLibro=findViewById(R.id.nombre_libro);
            TextView textViewNombreBiblio=findViewById(R.id.nombre_biblioteca);
            textViewNombreLibro.setText(titulo);
            textViewNombreBiblio.setText(biblio);
            final Button btnReserva= (Button) findViewById(R.id.buttonAccpReser);
            btnReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


    }

    private boolean validadorPrestamo(String cant){
        boolean valido;
        try{
            if(Integer.parseInt(cant)>0){
                valido=true;
            }else{
                valido=false;
            }
        }catch(NumberFormatException n){
            valido=false;
        }
        return valido;
    }


    private void agregarReserva(){
        ReservaMaterial reservaMaterial=new ReservaMaterial(1,2,3);
        new FireBaseDataBaseBiblitecaHelper().addReserva(reservaMaterial);
        /*new FireBaseDataBaseBiblitecaHelper().readReserva(new FireBaseDataBaseBiblitecaHelper.DataStatus() {
            @Override
            public void dataLoaded() {
                //Log.d("Tq", String.valueOf(listaBibliotecas.size()));
                new RecycleViewBibliotecaConfig().setConfig(BibliotecaConfig, contexto,listaBibliotecas,keys);
            }

            @Override
            public void dataInserted() {

            }

            @Override
            public void dataDeleted() {

            }

            @Override
            public void dataUpdated() {

            }
        });*/
    }
}
