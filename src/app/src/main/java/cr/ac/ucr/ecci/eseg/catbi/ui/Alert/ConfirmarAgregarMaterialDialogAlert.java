package cr.ac.ucr.ecci.eseg.catbi.ui.Alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;

public class ConfirmarAgregarMaterialDialogAlert extends AppCompatDialogFragment {
    // Funcion que crea la alerta de confirmacion y para agregar en la base de datos
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alterReserva= new AlertDialog.Builder(getActivity());
        final String[] mat= getArguments().getStringArray("material");
        final Material newMaterial=new Material(null,mat[0],mat[1],mat[2],mat[3],mat[4],mat[5],mat[6],mat[7]);
        alterReserva.setTitle("¿Desea agregar este material?")
                .setMessage("Presione si para agregar el material")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
                        boolean agregado=new FireBaseDataBaseBiblitecaHelper().addMaterial(newMaterial,getContext());
                        if(agregado){
                            Toast.makeText(getActivity(), "Se agrego el material" , Toast.LENGTH_SHORT).show();
                            retornar();
                        }else{
                            Toast.makeText(getActivity(), "Ha ocurrido un error al agregar el nuevo material" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Se cancela el agregado de datos",Toast.LENGTH_SHORT).show();
            }
        });
        return alterReserva.create();
    }
    // funcion para retornar a la actividad principal
    private void retornar(){
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
