package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cr.ac.ucr.ecci.eseg.catbi.ui.Notificaciones.NotificacionReciever;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);

    }

    public void setCorreo(String correoUsuario) {
        prefs.edit().putString("correoUsuarioActual", correoUsuario).commit();
    }

    public String getCorreo() {
        String correo = prefs.getString("correoUsuarioActual","");
        return correo;
    }

    public void removeSession(){
        prefs.edit().putString("correoUsuarioActual", "").commit();
    }

    public void setAlarmaActiva(){
        prefs.edit().putBoolean("alarmaActiva",true).commit();
    }

    public Boolean getAlarmaActiva(){
        return prefs.getBoolean("alarmaActiva", false);
    }

    public void removeAlarmaActiva(Context context){
        Intent intent = new Intent(context, NotificacionReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        prefs.edit().putBoolean("alarmaActiva",false).commit();
    }
}
