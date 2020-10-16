package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

public class Reservacion {
    private String correoUsuario;
    private String tituloMaterial;
    private int fechaLimite;
    private int materialID;
    private int usuarioID;

    public Reservacion() {
    }

    public Reservacion(String correoUsuario, String tituloMaterial, int diasReserva, int materialID, int usuarioID) {
        this.correoUsuario = correoUsuario;
        this.tituloMaterial = tituloMaterial;
        this.fechaLimite = diasReserva;
        this.materialID = materialID;
        this.usuarioID = usuarioID;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTituloMaterial() {
        return tituloMaterial;
    }

    public void setTituloMaterial(String tituloMaterial) {
        this.tituloMaterial = tituloMaterial;
    }

    public int getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(int fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
