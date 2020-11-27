package cr.ac.ucr.ecci.eseg.catbi;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class CerrarSesionTest {
    public static final String CORREO="gerald.bermudez@ucr.ac.cr";
    public static final String CONTRASENA = "seguridad";
    public static final String TITULO_LOGIN = "Inicio Sesión";

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule(LoginActivity.class);

    // Prueba que verifica que el usuario Gerald Bermúdez pueda cerrar su sesión exitosamente.
    // Se hace login, luego se dirige al perfil, se cierra sesión y se verifica.
    @Test
    public void loginUsuarioAccesoPerfilCerrarSesion() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.editTextCorreo)).perform(replaceText(CORREO));
        onView(withId(R.id.editTextContrasena)).perform(replaceText(CONTRASENA));
        onView(withId(R.id.btnInicioSesion)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.nav_preguntas)).perform(click());
        Thread.sleep(5000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Cerrar sesión")).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.txtInicioSesion)).check(matches(withText(TITULO_LOGIN)));
    }
}
