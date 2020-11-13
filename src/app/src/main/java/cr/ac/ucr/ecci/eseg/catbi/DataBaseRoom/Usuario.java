package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey @NonNull
    private String correo;
    private String nombre;
    private String contraseña;
    private String rango;

    public Usuario() {}

    public Usuario(String correo, String nombre, String contraseña, String rango) {
        this.correo = correo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rango = rango;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
}
