package cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class Notificacion {
    public static final String CHANNEL_ID = "ESEG-CATBI";

    public void notificarReserva(Context context, String titulo, String message){
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID
        )
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle(titulo)
                .setContentText(message)
                .setAutoCancel(true);

        Intent intent = new Intent(context, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE
        );
        notificationManager.notify(0, builder.build());
    }

    public void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ESEG CATBI";
            String description = "Notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notificarLimiteReservas(Context context, List<Reservacion> reservaciones) {
        Reservacion reservacionMasProxima = getReservacionMasProxima(reservaciones);
        String titulo;
        String mensaje;
        if(reservacionMasProxima != null){
            int diasRestantes = getDiasRestantes(reservacionMasProxima.getFechaLimite());

            titulo = "Recordatorio de vencimiento";
            mensaje = "El material '"+reservacionMasProxima.getTituloMaterial()+"' vence en "+diasRestantes+" dias";
        }else{
            titulo = "Estás al día!";
            mensaje = "No tienes ninguna reserva proxima a vencer";
        }

        notificarReserva(context, titulo, mensaje);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservacion getReservacionMasProxima(List<Reservacion> reservaciones){
        Date fecha = new Date();
        Reservacion reservacionMasProxima = new Reservacion();

        int diasActuales = 0;
        int menorActual = 0;

        for (int i = 0; i < reservaciones.size(); i++){
            diasActuales = getDiasRestantes(reservaciones.get(i).getFechaLimite());

            if(diasActuales != 0){
                if(diasActuales < menorActual || (diasActuales != 0 && menorActual == 0)){
                    menorActual = diasActuales;
                    reservacionMasProxima = reservaciones.get(i);
                }
            }else if (i == 0){
                menorActual = diasActuales;
                reservacionMasProxima = reservaciones.get(i);
            }
        }

        return (menorActual != 0) ? reservacionMasProxima : null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getDiasRestantes(String fecha){
        LocalDate fechaActual = LocalDate.now();
        fechaActual.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate fechaLimite = LocalDate.parse(fecha,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int noOfDaysBetween = (int) ChronoUnit.DAYS.between(fechaActual, fechaLimite);

        return  noOfDaysBetween > 0 ? noOfDaysBetween : 0;
    }
}
