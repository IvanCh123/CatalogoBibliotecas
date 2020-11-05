package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.EditarActivity;
import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

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
        textViewContentID.setText(materialRecibido.getID());
        textViewContentTitulo.setText(materialRecibido.getTitulo());
        textViewContentAutor.setText(materialRecibido.getAutor());
        textViewContentColeccion.setText(materialRecibido.getColeccion());
        textViewContentIdioma.setText(materialRecibido.getIdioma());
        textViewContentTipo.setText(materialRecibido.getFormato());
        textViewContentBiblioteca.setText(materialRecibido.getBiblioteca());


        final Button btnReserva= (Button) findViewById(R.id.button_reserva);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            String id=materialRecibido.getID();
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
                editarMaterial();
                break;
            case R.id.eliminar_material:
                eliminarMaterial();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void eliminarMaterial() {
        FireBaseDataBaseBiblitecaHelper db = new FireBaseDataBaseBiblitecaHelper();
        Material materialRecibido = (Material) getIntent().getSerializableExtra("materialClickeado");
        db.eliminarMaterial(materialRecibido.getID());
    }

    private void editarMaterial() {
        Material materialRecibido = (Material) getIntent().getSerializableExtra("materialClickeado");

        Intent intent = new Intent(getApplicationContext(), EditarActivity.class);
        intent.putExtra("materialClickeado", materialRecibido);
        startActivity(intent);
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
       // Context c=getContext();
        /*Intent intent1 = new Intent(this,RevervaLibros.class);
        intent1.putExtra("biblio",biblioteca);
        intent1.putExtra("id",id);
        intent1.putExtra("titulo",titulo);
        intent1.putExtra("user",user);
        this.startActivities(new Intent[]{intent1});*/
    }
}