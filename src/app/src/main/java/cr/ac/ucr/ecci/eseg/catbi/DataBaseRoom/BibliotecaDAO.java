package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BibliotecaDAO {
    // Me traigo todas las bibliotecas que hay en la BD
    @Query("SELECT * FROM Biblioteca")
    List<Biblioteca> leerBibliotecas();

    // Consulto una biblioteca a partir de su ID
    @Query("SELECT * FROM Biblioteca WHERE bibliotecaID LIKE :bibliotecaID LIMIT 1")
    Biblioteca leerPorID(String bibliotecaID);

    // Inserto biblioteca
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertar(Biblioteca... bibliotecas);

    // Borro una biblioteca
    @Delete
    void borrar(Biblioteca biblioteca);

    // Actualizo una biblioteca
    @Update
    void actualizar(Biblioteca biblioteca);
}
