package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
}
