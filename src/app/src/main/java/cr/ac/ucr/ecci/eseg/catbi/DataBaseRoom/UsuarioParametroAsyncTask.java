package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import java.util.List;

public class UsuarioParametroAsyncTask {
    private String correo;
    private UsuarioDataStatus usuarioStatus;

    public interface UsuarioDataStatus {
        void DataIsLoaded(Usuario usuario);
    }

    public UsuarioParametroAsyncTask(String correo, UsuarioDataStatus usuarioStatus) {
        this.correo = correo;
        this.usuarioStatus = usuarioStatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public UsuarioDataStatus getUsuarioStatus() {
        return usuarioStatus;
    }

    public void setUsuarioStatus(UsuarioDataStatus usuarioStatus) {
        this.usuarioStatus = usuarioStatus;
    }
}
