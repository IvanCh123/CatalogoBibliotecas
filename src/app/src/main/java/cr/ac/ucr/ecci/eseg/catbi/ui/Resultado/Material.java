package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

public class Material {
    private String autor;
    private String año;
    private String cantidad;
    private String coleccion;
    private String formato;
    private String titulo;

    public Material() {}

    public Material(String autor, String año, String cantidad, String coleccion, String formato, String titulo) {
        this.autor = autor;
        this.año = año;
        this.cantidad = cantidad;
        this.coleccion = coleccion;
        this.formato = formato;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
