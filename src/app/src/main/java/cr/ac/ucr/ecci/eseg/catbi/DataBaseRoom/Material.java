package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import java.io.Serializable;

public class Material implements Serializable {

    private String materialID;
    private String autor;
    private String año;
    private String cantidad;
    private String coleccion;
    private String formato;
    private String titulo;
    private String idioma;
    private String biblioteca;

    public Material() {}

    public Material(String materialID, String autor, String año, String cantidad, String coleccion, String formato, String titulo, String idioma, String biblioteca) {
        this.materialID = materialID;
        this.autor = autor;
        this.año = año;
        this.cantidad = cantidad;
        this.coleccion = coleccion;
        this.formato = formato;
        this.titulo = titulo;
        this.idioma = idioma;
        this.biblioteca = biblioteca;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
    }
    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }
}
