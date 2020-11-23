package cr.ac.ucr.ecci.eseg.catbi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

public class UsuarioAdministradorTest {
    public static final String CORREO="sebastian.otarola@ucr.ac.cr";
    public static final String CONTRASENA = "seguridad";
    public static final String TITULO_AGREGAR_MAT = "Agregar nuevo material";
    private static final String RANGO = "administrador";
    private FireBaseDataBaseBiblitecaHelper mFireBaseDataBaseBiblitecaHelper;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule(LoginActivity.class);
    // Prueba que verifica que un usuario administrador en este caso Sebastián Otárola tiene acceso a agregar materiales
    // Se hace login y después se verifica que pueda accesar a la pantalla de agregar materiales
    @Test
    public void loginUsuarioAdministradorAcessoAgregarMat() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.editTextCorreo)).perform(replaceText(CORREO));
        onView(withId(R.id.editTextContrasena)).perform(replaceText(CONTRASENA));
        onView(withId(R.id.btnInicioSesion)).perform(click());
        Thread.sleep(5000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Agregar Material")).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.title_agregar_material)).check(matches(withText(TITULO_AGREGAR_MAT)));;
    }

    @Test
    public void comprobarRangoUsuarioAdministrador() throws InterruptedException {
        mFireBaseDataBaseBiblitecaHelper = new FireBaseDataBaseBiblitecaHelper();
        mFireBaseDataBaseBiblitecaHelper.readUsuarios(new FireBaseDataBaseBiblitecaHelper.UsuariosDataStatus() {
            @Override
            public void DataIsLoaded(Usuario usuarioP) {
                assertEquals(RANGO, usuarioP.getRango());
            }
        }, CORREO);
    }



}

