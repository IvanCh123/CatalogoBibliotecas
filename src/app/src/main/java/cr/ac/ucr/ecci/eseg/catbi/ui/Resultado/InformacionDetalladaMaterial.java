package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.EditarActivity;
import cr.ac.ucr.ecci.eseg.catbi.ui.Alert.ConfirmarReservaDialogAlert;
import cr.ac.ucr.ecci.eseg.catbi.ui.Alert.EliminarDialogAlert;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class InformacionDetalladaMaterial extends AppCompatActivity {
    private static final String TAG = "InformacionDetalladaMaterial";
    private Button btnReserva;
    private Material materialActual = new Material();

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_detallada_material);
        Log.d(TAG, "onCreate: called.");

        materialActual = (Material) getIntent().getSerializableExtra("materialClickeado");
        setMaterial(materialActual);

        final Button btnReserva= (Button) findViewById(R.id.button_reserva);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            String id=materialActual.getMaterialID();
            String biblioteca=materialActual.getBiblioteca();
            String titulo=materialActual.getTitulo();
            String user=" ";
            String cant=materialActual.getCantidad();


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Material materialRecibido = (Material) data.getSerializableExtra("materialActualizado");
            this.materialActual = materialRecibido;
            setMaterial(materialActual);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opciones_material, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.editar_material:
                if(hayConexionAInternet()){
                    editarMaterial();
                }else{
                    Toast.makeText(getApplicationContext(),"La edición de materiales solo se pueden realizar si el dispositivo tiene conexión a internet",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.eliminar_material:
                if(hayConexionAInternet()){
                    eliminarMaterial();
                }else{
                    Toast.makeText(getApplicationContext(),"La eliminación de materiales solo se pueden realizar si el dispositivo tiene conexión a internet",Toast.LENGTH_LONG).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Material getMaterialActual(){
        return this.materialActual;
    }

    public void setMaterial(Material materialRecibido){
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
    }


    private void eliminarMaterial() {
        Material materialRecibido = getMaterialActual();

        EliminarDialogAlert eliminarDialogAlert = new EliminarDialogAlert(materialRecibido.getMaterialID());

        eliminarDialogAlert.show(getSupportFragmentManager(), "Confirmar");
    }

    private void editarMaterial() {
        Material materialRecibido = getMaterialActual();

        Intent intent = new Intent(getApplicationContext(), EditarActivity.class);
        intent.putExtra("materialClickeado", materialRecibido);
        startActivityForResult(intent, 1);
    }

    public void confirmarReserva(String id, String biblioteca, String titulo, String user, String cant){
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