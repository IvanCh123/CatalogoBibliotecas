package cr.ac.ucr.ecci.eseg.catbi.ui.Reserva;

import java.util.Date;

public class ReservaMaterial {
    private String correoUsuario;
    private String fechaLimite;
    private String materialID;
    private String tituloMaterial;
    private String usuarioID;

    public ReservaMaterial() {
    }

    public ReservaMaterial(String correoUsuario, String fechaLimite, String materialID, String tituloMaterial, String usuarioID) {
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

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public String getUsuarioID() {
        return usuarioID;
    }
}
