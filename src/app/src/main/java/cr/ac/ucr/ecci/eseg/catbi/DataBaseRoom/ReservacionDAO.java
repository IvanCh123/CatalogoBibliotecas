package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReservacionDAO {
    // Consulto un usuario a partir de su correo
    @Query("SELECT * FROM Reservacion WHERE correoUsuario LIKE :correo")
    List<Reservacion> leerPorCorreo(String correo);

    // Inserto reservaciones
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertar(Reservacion... reservacion);

    // Borro una reservacion
    @Delete
    void borrar(Reservacion reservacion);

    // Actualizo una reservacion
    @Update
    void actualizar(Reservacion reservacion);
}
