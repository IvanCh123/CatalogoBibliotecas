package cr.ac.ucr.ecci.eseg.catbi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

public class UsuarioNormalTest {
    private static final String CORREO="gerald.bermudez@ucr.ac.cr";
    private static final String RANGO = "normal";
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule(MainActivity.class);
    // Prueba que verifica el rango de un usuario en la app
    @Test
    public void comprobarRangoUsuarioNormal() throws InterruptedException {
        mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBiblitecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus() {
            @Override
            public void DataIsLoaded(Usuario usuarioP) {
                assertEquals(RANGO, usuarioP.getRango());
            }
        }, CORREO);
    }
}
