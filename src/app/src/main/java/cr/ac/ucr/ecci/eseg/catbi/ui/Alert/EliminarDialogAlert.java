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

public class EliminarDialogAlert extends AppCompatDialogFragment {

    String idMaterial;

    public EliminarDialogAlert(String idMaterial){
        this.idMaterial = idMaterial;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alterEliminacion = new AlertDialog.Builder(getActivity());
        alterEliminacion.setTitle("¿Desea eliminar este material?")
                .setMessage("Presione si para eliminar el material")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean eliminado =new FireBaseDataBaseBiblitecaHelper().eliminarMaterial(idMaterial);
                        if(eliminado){
                            Toast.makeText(getActivity(), "Se eliminó material correctamente" , Toast.LENGTH_SHORT).show();
                            retornar();
                        }else{
                            Toast.makeText(getActivity(), "Ha ocurrido un error al eliminar el material" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return alterEliminacion.create();
    }

    private void retornar(){
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
