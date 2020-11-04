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

    public void readBibliotecas (BibliotecaDataStatus dataStatus){
        new leerBibliotecas().execute(dataStatus);
    }

    public void realizarFiltradoConColeccionLocal(MaterialParametroAsyncTask parametroAsyncTask){
        new leerCamposBusqueda().execute(parametroAsyncTask);
    }

    public void realizarFiltradoSinColeccionLocal(MaterialParametroAsyncTask parametroAsyncTask){
        new leerCamposBusquedaSinColeccion().execute(parametroAsyncTask);
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



}

