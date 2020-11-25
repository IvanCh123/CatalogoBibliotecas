package cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class Notificaciones {
    private NotificacionReciever notificacionReciever;

    public Notificaciones(){
        this.notificacionReciever = new NotificacionReciever();
    }

    public void generarRecordatorioDiario(Context context, String correo){
        Calendar calendar  = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,13);
        calendar.set(Calendar.MINUTE,30);

        Intent intent = new Intent(context, NotificacionReciever.class);
        intent.putExtra("correo", correo);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generarNotificacion(Context context, String correo){
        this.notificacionReciever.generarNotificacion(context, correo);
    }
}
