package cr.ac.ucr.ecci.eseg.catbi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
        final String biblio= getArguments().getString("biblio");
        final String id= getArguments().getString("id");
        final String titu= getArguments().getString("titulo");
        final String user= getArguments().getString("user");
        final String cant= getArguments().getString("cant");
        //final String finalB =bilio+" " +titu ;
        alterReserva.setTitle("¿Desea reservar este libro?")
                .setMessage("Presione si para continuar el proceso de reserva")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), finalB,Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(),"Se presiono SI",Toast.LENGTH_SHORT).show();
                        Context c=getContext();
                        Intent intent1 = new Intent(c, RevervaLibros.class);
                        intent1.putExtra("biblio",biblio);
                        intent1.putExtra("id",id);
                        intent1.putExtra("titulo",titu);
                        intent1.putExtra("user",user);
                        intent1.putExtra("cant",cant);
                        c.startActivity(intent1);
                        //Toast.makeText(getActivity(), finalB,Toast.LENGTH_SHORT).show();
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
/*
Intent intent1 = new Intent(contexto,
                            BibliotecaUbicacion.class);//BibliotecaUbicacion.class
                    intent1.putExtra("name", name);
                    intent1.putExtra("L1", L1);
                    intent1.putExtra("L2", L2);
                    contexto.startActivity(intent1);

 */