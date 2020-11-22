package cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import cr.ac.ucr.ecci.eseg.catbi.MainActivity;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Perfil.PerfilFragment;

public class Notificacion {
    public static final String CHANNEL_ID = "ESEG-CATBI";

    public void notificarReserva(Context context, String message){
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID
        )
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Confirmación de reserva")
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
}
