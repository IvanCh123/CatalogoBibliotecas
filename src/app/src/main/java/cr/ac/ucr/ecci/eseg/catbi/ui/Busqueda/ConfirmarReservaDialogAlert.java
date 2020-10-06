package cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConfirmarReservaDialogAlert extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alterReserva= new AlertDialog.Builder(getActivity());
        alterReserva.setTitle("¿Desea reservar este libro?")
                .setMessage("Presione si para continuar el proceso de reserva")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Se presiono Si",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Se presiono No",Toast.LENGTH_SHORT).show();
            }
        });
        return alterReserva.create();

    }
}
