package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import java.util.List;

public class ObjetoParametroAsyncTask {
    private String coleccion;
    private String palabraClave;
    private String campoBusqueda;
    private MaterialDataStatus materialStatus;

    public interface MaterialDataStatus {
        void DataIsLoaded(List<Material> materiales);
    }

    public ObjetoParametroAsyncTask(String coleccion, String palabraClave, String campoBusqueda, MaterialDataStatus materialStatus) {
        this.coleccion = coleccion;
        this.palabraClave = palabraClave;
        this.campoBusqueda = campoBusqueda;
        this.materialStatus = materialStatus;
    }



    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }

    public MaterialDataStatus getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(MaterialDataStatus materialStatus) {
        this.materialStatus = materialStatus;
    }
}
