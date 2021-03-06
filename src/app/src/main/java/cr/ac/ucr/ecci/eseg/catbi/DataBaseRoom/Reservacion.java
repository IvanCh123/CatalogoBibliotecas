package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class Reservacion {


    @PrimaryKey @NonNull
    private String reservacionID;
    private String correoUsuario;
    private String fechaLimite;
    private String materialID;
    private String tituloMaterial;
    private String usuarioID;

    public Reservacion(){

    }

    public Reservacion(String correoUsuario, String fechaLimite, String materialID, String tituloMaterial, String usuarioID) {
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

    public String getTituloMaterial() {
        return tituloMaterial;
    }

    public void setTituloMaterial(String tituloMaterial) {
        this.tituloMaterial = tituloMaterial;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getReservacionID() {
        return reservacionID;
    }

    public void setReservacionID( String reservacionID) {
        this.reservacionID = reservacionID;
    }
}
