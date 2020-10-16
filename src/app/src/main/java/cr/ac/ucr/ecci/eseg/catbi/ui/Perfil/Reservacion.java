package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

public class Reservacion {
    private String correoUsuario;
    private String tituloMaterial;
    private String fechaLimite;
    private String materialID;
    private String usuarioID;

    public Reservacion() {
    }

    public Reservacion(String correoUsuario, String tituloMaterial, String fechaLimite, String materialID, String usuarioID) {
        this.correoUsuario = correoUsuario;
        this.tituloMaterial = tituloMaterial;
        this.fechaLimite = fechaLimite;
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

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }
}
