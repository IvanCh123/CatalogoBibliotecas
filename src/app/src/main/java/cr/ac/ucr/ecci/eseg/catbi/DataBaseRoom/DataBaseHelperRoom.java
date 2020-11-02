package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.AppDataBase;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.LoginActivity;
import cr.ac.ucr.ecci.eseg.catbi.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperRoom {
    private AppDataBase dbLocal;
    private Context context;
    private List<Material> materiales;

    public DataBaseHelperRoom (Context context){
        this.context = context;
        this.dbLocal = Room.databaseBuilder(context,
                AppDataBase.class, "bibliotecasStorage").fallbackToDestructiveMigration().build();
    }


    public void readMaterialLocal (ObjetoParametroAsyncTask parametroAsyncTask){
        List<Material> consulta = new ArrayList<>();
        if(parametroAsyncTask.getColeccion().equalsIgnoreCase("todas")){
            realizarFiltradoSinColeccionLocal(parametroAsyncTask);
        }/*else{
            realizarFiltradoConColeccionLocal(parametroAsyncTask);
        }*/
    }

    public void realizarFiltradoConColeccionLocal(String filtro []){
        String palabraClave = filtro[0];
        String campoBusqueda = filtro[1];
        String colecionFiltro = filtro[2];
        new leerCamposBusqueda().execute(colecionFiltro,palabraClave,campoBusqueda);
    }

    public void realizarFiltradoSinColeccionLocal(ObjetoParametroAsyncTask parametroAsyncTask){
        new leerCamposBusquedaSinColeccion().execute(parametroAsyncTask);
    }

    private class leerCamposBusqueda extends AsyncTask<String, Void, List<Material>> {
        @Override
        protected List<Material> doInBackground(String... filtro) {
            String coleccion = filtro[0];
            String palabraClave = filtro[1];
            String campoBusqueda = filtro [2];
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
            return materialesConsultados;
        }

        @Override
        protected void onPostExecute(List<Material> materialesConsultados) {
            if(materialesConsultados != null){
                materiales = materialesConsultados;
            }else {
                List<Material> materialVacio = new ArrayList<>();
                materiales = materialVacio;
            }
        }
    }

    private class leerCamposBusquedaSinColeccion extends AsyncTask<ObjetoParametroAsyncTask,Void, Void> {
        @Override
        protected Void doInBackground(ObjetoParametroAsyncTask... filtro) {
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
            ObjetoParametroAsyncTask.MaterialDataStatus materialDataStatus = filtro[0].getMaterialStatus();
            materialDataStatus.DataIsLoaded(materialesConsultados);
            return  null;
        }



    }



}

