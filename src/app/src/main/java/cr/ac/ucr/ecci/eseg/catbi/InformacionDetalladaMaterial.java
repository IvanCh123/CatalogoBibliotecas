package cr.ac.ucr.ecci.eseg.catbi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class InformacionDetalladaMaterial extends AppCompatActivity {
    private static final String TAG = "InformacionDetalladaMaterial";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_detallada_material);
        Log.d(TAG, "onCreate: called.");

        final Material materialRecibido = (Material) getIntent().getSerializableExtra("materialClickeado");

        TextView textViewNombreMaterial = findViewById(R.id.textViewNombreMaterial);
        TextView textViewContentID = findViewById(R.id.textViewContentID);
        TextView textViewContentTitulo = findViewById(R.id.textViewContentTitulo);
        TextView textViewContentAutor = findViewById(R.id.textViewContentAutor);
        TextView textViewContentColeccion = findViewById(R.id.textViewContentColeccion);
        TextView textViewContentIdioma = findViewById(R.id.textViewContentIdioma);
        TextView textViewContentTipo = findViewById(R.id.textViewContentTipo);
        TextView textViewContentBiblioteca = findViewById(R.id.textViewContentBiblioteca);

        textViewNombreMaterial.setText(materialRecibido.getTitulo());
        textViewContentID.setText(materialRecibido.getMaterialID());
        textViewContentTitulo.setText(materialRecibido.getTitulo());
        textViewContentAutor.setText(materialRecibido.getAutor());
        textViewContentColeccion.setText(materialRecibido.getColeccion());
        textViewContentIdioma.setText(materialRecibido.getIdioma());
        textViewContentTipo.setText(materialRecibido.getFormato());
        textViewContentBiblioteca.setText(materialRecibido.getBiblioteca());


        final Button btnReserva= (Button) findViewById(R.id.button_reserva);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            String id=materialRecibido.getMaterialID();
            String biblioteca=materialRecibido.getBiblioteca();
            String titulo=materialRecibido.getTitulo();
            String user=" ";
            String cant=materialRecibido.getCantidad();


            @Override
            public void onClick(View v) {
                //Log.v("button","1");
                confirmarReserva(id,biblioteca,titulo,user,cant);
            }
        });
    }

    public void confirmarReserva(String id,String biblioteca,String titulo,String user,String cant){
        ConfirmarReservaDialogAlert confirmarReservaDialogAlert=new ConfirmarReservaDialogAlert();
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("biblio",biblioteca); // set msg here
        bundle.putString("titulo",titulo);
        bundle.putString("user",user);
        bundle.putString("cant",cant);
        confirmarReservaDialogAlert.setArguments(bundle);
        confirmarReservaDialogAlert.show(getSupportFragmentManager(),"Confirmar");
       // Context c=getContext();
        /*Intent intent1 = new Intent(this,RevervaLibros.class);
        intent1.putExtra("biblio",biblioteca);
        intent1.putExtra("id",id);
        intent1.putExtra("titulo",titulo);
        intent1.putExtra("user",user);
        this.startActivities(new Intent[]{intent1});*/
    }
}