package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RevervaLibros extends AppCompatActivity {
    private String biblio;
    private String titulo;
    private String id;
    private String user;

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

            }
        });

    }

}
