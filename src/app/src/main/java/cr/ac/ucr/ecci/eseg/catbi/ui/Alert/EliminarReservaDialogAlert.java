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

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.ReservaFila;

public class EliminarReservaDialogAlert extends AppCompatDialogFragment {
    List<ReservaFila>listReserva;
    String mensaje;
    public EliminarReservaDialogAlert(List<ReservaFila>listReserva){
        this.listReserva = listReserva;
        int t=0;
        for(int i=0;i<listReserva.size();i++){
            if(listReserva.get(i).isCheck()){
                t++;
            }
        }
        mensaje="¿Desea eliminar las siguientes "+String.valueOf(t)+" reservas?";
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alterReserva= new AlertDialog.Builder(getActivity());
        alterReserva.setTitle(mensaje)
                .setMessage("Presione si para agregar el material")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new FireBaseDataBaseBiblitecaHelper().contarHijosMaterial();
                        boolean agregado=new FireBaseDataBaseBiblitecaHelper().eliminarReservas(listReserva);
                        if(agregado){
                            Toast.makeText(getActivity(), "Se elimino material" , Toast.LENGTH_SHORT).show();
                            retornar();
                        }else{
                            Toast.makeText(getActivity(), "Ha ocurrido un error al eliminar reservas" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Se cancela el la eliminacion de reservas",Toast.LENGTH_SHORT).show();
            }
        });
        return alterReserva.create();
    }

    private void retornar(){
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
