package cr.ac.ucr.ecci.eseg.catbi.ui.Reserva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones.NotificacionReciever;

public class RevervaLibros extends AppCompatActivity {
    private String biblio;
    private String titulo;
    private String id;
    private String user;
    private String cant;
    private NotificacionReciever notificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificacion = new NotificacionReciever();
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
                         String tituloNotificacion = "Confirmaci??n de reserva";
                         String mensaje = "'"+titulo+"' se ha reservado exitosamente.";
                         notificacion.notificarReserva(102, getApplicationContext(), tituloNotificacion, mensaje);
                     }else {
                         String titulo = "Error en la reserva";
                         String mensaje = "Ha habido una falla en la reserva.";
                         notificacion.notificarReserva(102, getApplicationContext(), titulo, mensaje);
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
        Reservacion reservacion =new Reservacion("",dias,id,titulo,"0");
        boolean b= new FireBaseDataBaseBiblitecaHelper().addReserva(reservacion,cant,getApplicationContext());

        return b;
    }

    private void retornar(){
        startActivity(new Intent(RevervaLibros.this, MainActivity.class));
    }
}
