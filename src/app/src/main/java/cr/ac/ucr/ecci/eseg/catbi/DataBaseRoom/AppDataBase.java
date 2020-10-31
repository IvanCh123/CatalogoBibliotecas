package cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom;
import androidx.room.Database;
import androidx.room.RoomDatabase;

// Aca tenemos que definir Biblioteca, Material, Usuario....
@Database(entities = {Biblioteca.class, Material.class, Usuario.class, Reservacion.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract BibliotecaDAO bibliotecaDAO();
    public abstract MaterialDAO materialDAO();
    public abstract UsuarioDAO usuarioDAO();
    public abstract ReservacionDAO reservacionDAO();
}
