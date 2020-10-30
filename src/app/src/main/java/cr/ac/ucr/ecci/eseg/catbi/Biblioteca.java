package cr.ac.ucr.ecci.eseg.catbi;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Biblioteca {
    @PrimaryKey (autoGenerate = true) @NonNull
    private int bibliotecaID;
    private String nombre;
    private String horario;
    private String telefono;
    private double latitud;
    private double longitud;

    public Biblioteca(int bibliotecaID, String nombre, String horario, String telefono, double latitud, double longitud) {
        this.bibliotecaID = bibliotecaID;
        this.nombre = nombre;
        this.horario = horario;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Biblioteca() {
    }

    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID(int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
