package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

public class Usuarios {
    private String correo;
    private String nombre;

    public Usuarios() {}

    public Usuarios(String correo, String nombre) {
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
