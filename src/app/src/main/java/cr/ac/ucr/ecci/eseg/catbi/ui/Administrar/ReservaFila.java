package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;

public class ReservaFila {
    private String materialTitulo;
    private String diasReserva;
    private boolean check;
    private String idReserva;
    Reservacion reservacion;

    public ReservaFila() {
    }

    public ReservaFila(String materialTitulo, String diasReserva, boolean check, String idReserva) {
        this.materialTitulo = materialTitulo;
        this.diasReserva = diasReserva;
        this.check = check;
        this.idReserva = idReserva;
    }

    public ReservaFila(boolean check, Reservacion reservacion) {
        this.check = check;
        this.reservacion = reservacion;
    }

    public String getMaterialTitulo() {
        return materialTitulo;
    }

    public void setMaterialTitulo(String materialTitulo) {
        this.materialTitulo = materialTitulo;
    }

    public String getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(String diasReserva) {
        this.diasReserva = diasReserva;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }
}
