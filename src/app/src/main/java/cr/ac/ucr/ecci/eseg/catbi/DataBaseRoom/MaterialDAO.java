package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;
@Dao
public interface MaterialDAO {
    // Me traigo todas las bibliotecas que hay en la BD
    @Query("SELECT * FROM Material")
    List<Material> leerMateriales();

    // Consulto un material a partir de su ID
    @Query("SELECT * FROM Material WHERE materialID LIKE :materialID LIMIT 1")
    Material leerPorID(String materialID);

    // Busqueda por campo todos y coleccion general
    // SELECT * FROM MATERIAL WHERE coleccion = 'General' AND (titulo LIKE '%palabraClave%' OR autor LIKE '%palabraClave' OR idioma LIKE '%palabraClave%')
    @ Query("SELECT * FROM Material WHERE coleccion = :coleccionRecuperada  AND (titulo LIKE '%'||:palabraClave||'%' OR autor LIKE'%'||:palabraClave||'%' OR idioma LIKE '%'||:palabraClave||'%')")
    List<Material> leerColeccionTodos(String coleccionRecuperada,String palabraClave);

    // Busqueda por campo titulo y colección
    @Query("SELECT * FROM Material WHERE coleccion =:coleccionRecuperada AND (titulo LIKE '%'||:palabraClave||'%')")
    List<Material> leerColeccionTitulo(String coleccionRecuperada,String palabraClave);

    // Busqueda por campo autor y coleccion general
    @Query("SELECT * FROM Material WHERE coleccion =:coleccionRecuperada AND (autor LIKE '%'||:palabraClave||'%')")
    List<Material> leerColeccionAutor(String coleccionRecuperada,String palabraClave);

    // Busqueda por campo idioma y coleccion general
    @Query("SELECT * FROM Material WHERE coleccion =:coleccionRecuperada AND (idioma LIKE '%'||:palabraClave||'%')")
    List<Material> leerColeccionIdioma(String coleccionRecuperada,String palabraClave);
    // Inserto material
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertar(Material... materiales);

    // Borro un material
    @Delete
    void borrar(Material material);

    // Actualizo una biblioteca
    @Update
    void actualizar(Material material);

}
