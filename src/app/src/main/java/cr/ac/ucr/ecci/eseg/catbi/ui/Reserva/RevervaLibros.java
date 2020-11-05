package cr.ac.ucr.ecci.eseg.catbi.ui.Reserva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;

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
        biblio=intent.getStringExtra("biblio");
        titulo=intent.getStringExtra("titulo");
        id=intent.getStringExtra("id");
        user=intent.getStringExtra("user");
        cant=intent.getStringExtra("cant");
        final boolean[] reserva = new boolean[1];

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
                     reserva[0] = agregarReserva("15",id,titulo);
                     if(reserva[0]){
                         Toast.makeText(getApplicationContext(), "Reserva se ha realizado exitosamente" , Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(getApplicationContext(), "Ha habido una falla en la reserva" , Toast.LENGTH_SHORT).show();
                     }
                    retornar();
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
                    retornar();
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


    private boolean agregarReserva(String dias, String id,String titulo){
        ReservaMaterial reservaMaterial=new ReservaMaterial("",dias,id,titulo,"0");
        boolean b= new FireBaseDataBaseBiblitecaHelper().addReserva(reservaMaterial,cant);

        return b;
    }

    private void retornar(){
        startActivity(new Intent(RevervaLibros.this, MainActivity.class));
    }
}
