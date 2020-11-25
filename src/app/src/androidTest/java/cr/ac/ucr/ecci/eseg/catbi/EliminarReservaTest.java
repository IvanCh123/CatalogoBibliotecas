package cr.ac.ucr.ecci.eseg.catbi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.ReservaFila;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class EliminarReservaTest {
    public static final String TEST_STRING_USUARIO="Estudiante";
    public static final String TEST_STRING_MAIL="estudiante@ucr.ac.cr";
    public static final String TEST_STRING_MATERIAL="Cocori";
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule(LoginActivity.class);
    @Test
    public void EliminarReserva() throws InterruptedException {
        Thread.sleep(30000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Eliminar reserva")).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.nombre_usuario)).perform(replaceText(TEST_STRING_USUARIO));
        onView(withId(R.id.usuario_buscar)).perform(click());
        Thread.sleep(5000);
        onView(withText(TEST_STRING_MAIL)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.select_all)).perform(click());
        //onView(withText("Cocori"));
        //onView(withId(R.id.recyclerViewEliminarReserva)).check(matches(withText("Cocori")));
        //onData().inAdapterView(withId(R.id.recyclerViewEliminarReserva)).check(matches(isDisplayed()));
               /* instanceOf(ReservaFila.class))
                .atPosition(i)
                .perform(click());*/
        Thread.sleep(10000);
        onView(withId(R.id.aceptar_eliminar)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        Thread.sleep(5000);
        //onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO));
    }

}
