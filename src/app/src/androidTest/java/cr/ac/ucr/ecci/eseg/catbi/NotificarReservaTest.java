package cr.ac.ucr.ecci.eseg.catbi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.BaseDatos.FireBaseDataBaseBiblitecaHelper;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Session;
import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.ReservaFila;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class NotificarReservaTest {
    public static final String CORREO="estudiante@ucr.ac.cr";
    public static final String CONTRASENA = "ucr1234";
    public static final String TEST_STRING_TITULO="Pruebas Notificacion";
    private FireBaseDataBaseBiblitecaHelper db;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule(LoginActivity.class);

    @Test
    public void notificacionReservaNaterialCocori() throws InterruptedException {
        Session session = new Session(getApplicationContext());
        Thread.sleep(10000);
        if(session.getCorreo().equals("")){
            onView(withId(R.id.editTextCorreo)).perform(replaceText(CORREO));
            onView(withId(R.id.editTextContrasena)).perform(replaceText(CONTRASENA));
            onView(withId(R.id.btnInicioSesion)).perform(click());
            Thread.sleep(7000);
        }
        onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO));
        onView(withId(R.id.btnBuscar)).perform(click());
        Thread.sleep(5000);
        onView(withText(TEST_STRING_TITULO)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.button_reserva)).perform(click());
        Thread.sleep(2000);
        onView(withText("Si"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.buttonAccpReser)).perform(click());
        Thread.sleep(3000);

        Thread.sleep(5000);
    }
}
