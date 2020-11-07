package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperRoom {
    private AppDataBase dbLocal;
    private Context context;
    private List<Material> materiales;

    public interface BibliotecaDataStatus {
        void DataIsLoaded(List<Biblioteca> bibliotecas);
    }

    public DataBaseHelperRoom (Context context){
        this.context = context;
        this.dbLocal = Room.databaseBuilder(context,
                AppDataBase.class, "bibliotecasStorage").fallbackToDestructiveMigration().build();
    }

    public List<Material> getMateriales() {
        return materiales;
    }


    public void readMaterialLocal (MaterialParametroAsyncTask parametroAsyncTask){
        if(parametroAsyncTask.getColeccion().equalsIgnoreCase("todas")){
            realizarFiltradoSinColeccionLocal(parametroAsyncTask);
        }else {
            realizarFiltradoConColeccionLocal(parametroAsyncTask);
        }
    }

    public void borrarMaterial(String id){
        new borrarMaterial().execute(id);
    }

    public void readBibliotecas (BibliotecaDataStatus dataStatus){
        new leerBibliotecas().execute(dataStatus);
    }

    public void readReservacion (ReservacionParametroAsyncTask parametroAsyncTask){
        new leerReservaciones().execute(parametroAsyncTask);
    }

    public void readUsuario (UsuarioParametroAsyncTask parametroAsyncTask){
        new leerUsuario().execute(parametroAsyncTask);
    }

    public void realizarFiltradoConColeccionLocal(MaterialParametroAsyncTask parametroAsyncTask){
        new leerCamposBusqueda().execute(parametroAsyncTask);
    }

    public void realizarFiltradoSinColeccionLocal(MaterialParametroAsyncTask parametroAsyncTask){
        new leerCamposBusquedaSinColeccion().execute(parametroAsyncTask);
    }

    public void actualizarMaterial(Material material){
        new actualizarMaterial().execute(material);
    }

    private class leerCamposBusqueda extends AsyncTask<MaterialParametroAsyncTask, Void, Void> {
        @Override
        protected Void doInBackground(MaterialParametroAsyncTask... filtro) {
            String coleccion = filtro[0].getColeccion();
            String palabraClave = filtro[0].getPalabraClave();
            String campoBusqueda = filtro[0].getCampoBusqueda();
            List<Material> materialesConsultados = new ArrayList<>();
            switch (campoBusqueda){
                case "titulo":
                    materialesConsultados = dbLocal.materialDAO().leerColeccionTitulo(coleccion,palabraClave);
                    break;
                case "autor":
                    materialesConsultados = dbLocal.materialDAO().leerColeccionAutor(coleccion,palabraClave);
                    break;
                case "idioma":
                    materialesConsultados = dbLocal.materialDAO().leerColeccionIdioma(coleccion,palabraClave);
                    break;
                case "todo":
                    materialesConsultados = dbLocal.materialDAO().leerColeccionTodos(coleccion,palabraClave);
                    break;
            }
            materiales = materialesConsultados;
            MaterialParametroAsyncTask.MaterialDataStatus materialDataStatus = filtro[0].getMaterialStatus();
            materialDataStatus.DataIsLoaded(materialesConsultados);
            return  null;
        }


    }

    private class leerCamposBusquedaSinColeccion extends AsyncTask<MaterialParametroAsyncTask,Void, Void> {
        @Override
        protected Void doInBackground(MaterialParametroAsyncTask... filtro) {
            String palabraClave = filtro[0].getPalabraClave();
            String campoBusqueda = filtro[0].getCampoBusqueda();
            List<Material> materialesConsultados = new ArrayList<>();
            switch (campoBusqueda) {
                case "titulo":
                    materialesConsultados = dbLocal.materialDAO().leerSinColeccionTitulo(palabraClave);
                    break;
                case "autor":
                    materialesConsultados = dbLocal.materialDAO().leerSinColeccionAutor(palabraClave);
                    break;
                case "idioma":
                    materialesConsultados = dbLocal.materialDAO().leerSinColeccionIdioma(palabraClave);
                    break;
                case "todo":
                    materialesConsultados = dbLocal.materialDAO().leerSinColeccionTodos(palabraClave);
                    break;
            }
            materiales = materialesConsultados;
            MaterialParametroAsyncTask.MaterialDataStatus materialDataStatus = filtro[0].getMaterialStatus();
            materialDataStatus.DataIsLoaded(materialesConsultados);
            return  null;
        }
    }

    private class leerBibliotecas extends AsyncTask<BibliotecaDataStatus,Void, Void> {
        @Override
        protected Void doInBackground(BibliotecaDataStatus... bibliotecaDataStatus) {
            List<Biblioteca> BibliotecasConsultadas = new ArrayList<>();
            BibliotecasConsultadas = dbLocal.bibliotecaDAO().leerBibliotecas();
            bibliotecaDataStatus[0].DataIsLoaded(BibliotecasConsultadas);
            return null;
        }
    }

    private class leerReservaciones extends AsyncTask<ReservacionParametroAsyncTask,Void, Void> {
        @Override
        protected Void doInBackground(ReservacionParametroAsyncTask... reservacionParametro) {
            String correo = reservacionParametro[0].getCorreo();
            List<Reservacion> reservacionesConsultadas = new ArrayList<>();
            reservacionesConsultadas = dbLocal.reservacionDAO().leerPorCorreo(correo);
            ReservacionParametroAsyncTask.ReservacionDataStatus reservacionDataStatus = reservacionParametro[0].getReservacionStatus();
            reservacionDataStatus.DataIsLoaded(reservacionesConsultadas);
            return null;
        }
    }

    private class leerUsuario extends AsyncTask<UsuarioParametroAsyncTask,Void, Void> {
        @Override
        protected Void doInBackground(UsuarioParametroAsyncTask... usuarioParametro) {
            String correo = usuarioParametro[0].getCorreo();
            Usuario usuario = dbLocal.usuarioDAO().leerUsuario(correo);
            UsuarioParametroAsyncTask.UsuarioDataStatus reservacionDataStatus = usuarioParametro[0].getUsuarioStatus();
            reservacionDataStatus.DataIsLoaded(usuario);
            return null;
        }
    }

    private class borrarMaterial extends AsyncTask<String,Void, Void> {
        @Override
        protected Void doInBackground(String... parametro) {
            String materialID = parametro[0];
            dbLocal.materialDAO().borrarPorMaterialID(materialID);
            return null;
        }
    }

    private class actualizarMaterial extends AsyncTask<Material,Void, Void> {
        @Override
        protected Void doInBackground(Material... material) {
            dbLocal.materialDAO().actualizar(material[0]);
            return null;
        }
    }

}

