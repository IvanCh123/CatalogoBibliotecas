package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UsuarioDAO {

    // Consulto un usuario a partir de un correo y contraseña para autenticar
    @Query("SELECT * FROM Usuario WHERE correo LIKE :correo AND contraseña LIKE :contrasena LIMIT 1")
    Usuario leerAutenticacion(String correo, String contrasena);

    // Inserto usuarios
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertar(Usuario... usuarios);

    // Borro una usuario
    @Delete
    void borrar(Usuario usuario);

    // Actualizo una usuario
    @Update
    void actualizar(Usuario usuario);
}
