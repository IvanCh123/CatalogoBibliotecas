package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
public interface MaterialDAO {
    // Me traigo todas las bibliotecas que hay en la BD
    @Query("SELECT * FROM Material")
    List<Material> leerMateriales();

    // Consulto una biblioteca a partir de su ID
    @Query("SELECT * FROM Material WHERE materialID LIKE :materialID LIMIT 1")
    Material leerPorID(String materialID);

    // Consulto una biblioteca a partir de su titulo
    @Query("SELECT * FROM Material WHERE titulo LIKE :titulo LIMIT 1")
    Material leerPorTitulo(String titulo);

    // Inserto material
    @Insert
    void insertar(Material... materiales);

    // Borro un material
    @Delete
    void borrar(Material material);

    // Actualizo una biblioteca
    @Update
    void actualizar(Material material);

}
