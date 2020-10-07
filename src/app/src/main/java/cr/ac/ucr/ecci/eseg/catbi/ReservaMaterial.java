package cr.ac.ucr.ecci.eseg.catbi;

import java.util.Date;

public class ReservaMaterial {
    private String tituloMaterial;
    private int idMaterial;
    private Date fechaReserva;


    public ReservaMaterial() {
    }

    public ReservaMaterial(String tituloMaterial, int idMaterial, Date fechaReserva) {
        this.tituloMaterial = tituloMaterial;
        this.idMaterial = idMaterial;
        this.fechaReserva = fechaReserva;
    }

    public String getTituloMaterial() {
        return tituloMaterial;
    }

    public void setTituloMaterial(String tituloMaterial) {
        this.tituloMaterial = tituloMaterial;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
