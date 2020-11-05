package cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas;

public class ListarBibliotecas {
    private String horario;
    private double latitud;
    private double longitud;
    private String nombre;

    public ListarBibliotecas() {
    }

    public ListarBibliotecas(String horario, double latitud, double longitud, String nombre, String telefono) {
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    private String telefono;

}
