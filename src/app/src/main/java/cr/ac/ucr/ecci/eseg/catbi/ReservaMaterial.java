package cr.ac.ucr.ecci.eseg.catbi;

import java.util.Date;

public class ReservaMaterial {
   private int diasLimite;
    private int usuario;
    private int materialId;

    public ReservaMaterial() {
    }

    public ReservaMaterial(int diasLimite, int usuario, int materialId) {
        this.diasLimite = diasLimite;
        this.usuario = usuario;
        this.materialId = materialId;
    }

    public int getDiasLimite() {
        return diasLimite;
    }

    public void setDiasLimite(int diasLimite) {
        this.diasLimite = diasLimite;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
}
