package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

public class Usuario {
    private String correo;
    private String nombre;
    // Conversar si es necesario tener password para almacenarlo en room

    public Usuario() {}

    public Usuario(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
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
}
