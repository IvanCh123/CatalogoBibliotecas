package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import java.util.List;

public class ReservacionParametroAsyncTask {
    private String correo;
    private ReservacionDataStatus reservacionStatus;

    public interface ReservacionDataStatus {
        void DataIsLoaded(List<Reservacion> reservaciones);
    }

    public ReservacionParametroAsyncTask(String correo, ReservacionDataStatus reservacionStatus) {
        this.correo = correo;
        this.reservacionStatus = reservacionStatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ReservacionDataStatus getReservacionStatus() {
        return reservacionStatus;
    }

    public void setReservacionStatus(ReservacionDataStatus reservacionStatus) {
        this.reservacionStatus = reservacionStatus;
    }
}
