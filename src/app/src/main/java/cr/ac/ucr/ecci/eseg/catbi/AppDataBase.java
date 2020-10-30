package cr.ac.ucr.ecci.eseg.catbi;
import androidx.room.Database;
import androidx.room.TypeConverters;
import androidx.room.RoomDatabase;
// Aca tenemos que definir Biblioteca, Material, Usuario....
@Database(entities = {Biblioteca.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract BibliotecaDAO bibliotecaDAO();
    // public abstract MaterialDAO materialDAO();
    // public abstract UsuarioDAO  usuarioDAO();
    // ....

}
