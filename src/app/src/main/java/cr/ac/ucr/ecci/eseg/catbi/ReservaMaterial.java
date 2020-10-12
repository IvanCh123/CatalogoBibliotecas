package cr.ac.ucr.ecci.eseg.catbi;

import java.util.Date;

public class ReservaMaterial {
    private String correoUsuario;
    private int fechaLimite;
    private String materialID;
    private String tituloMaterial;
    private int usuarioID;

    public ReservaMaterial() {
    }

    public ReservaMaterial(String correoUsuario, int fechaLimite, String materialID, String tituloMaterial, int usuarioID) {
        this.correoUsuario = correoUsuario;
        this.fechaLimite = fechaLimite;
        this.materialID = materialID;
        this.tituloMaterial = tituloMaterial;
        this.usuarioID = usuarioID;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public int getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(int fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getTituloMaterial() {
        return tituloMaterial;
    }

    public void setTituloMaterial(String tituloMaterial) {
        this.tituloMaterial = tituloMaterial;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
