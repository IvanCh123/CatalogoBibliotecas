package cr.ac.ucr.ecci.eseg.catbi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class InformacionDetalladaMaterial extends AppCompatActivity {
    private static final String TAG = "InformacionDetalladaMaterial";
    private Button btnReserva;

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


        btnReserva= (Button) findViewById(R.id.button_reserva);
        btnReserva.setOnClickListener(new View.OnClickListener() {
        String id=materialRecibido.getMaterialID();
        String biblioteca=materialRecibido.getBiblioteca();
        String titulo=materialRecibido.getTitulo();
        String user=" ";
        String cant=materialRecibido.getCantidad();

        @Override
        public void onClick(View v) {
            if(hayConexionAInternet()){
                confirmarReserva(id,biblioteca,titulo,user,cant);
            }else{
                Toast.makeText(v.getContext(),"Las reservaciones solo se pueden realizar si el dispositivo tiene conexión a internet",Toast.LENGTH_LONG).show();
            }

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
    }
    public boolean hayConexionAInternet(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}