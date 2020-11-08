package cr.ac.ucr.ecci.eseg.catbi;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class EliminarMatTest {
    public static final String TEST_STRING_TITULO="Drácula";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule(MainActivity.class);
    @Test
    public void eliminarMaterial() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO));
        onView(withId(R.id.btnBuscar)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.titulo_textView)).check(matches(withText(TEST_STRING_TITULO)));
        onView(withText(TEST_STRING_TITULO)).perform(click());
        Thread.sleep(5000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Eliminar")).perform(click());
        Thread.sleep(5000);
        onView(withId(android.R.id.button1)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO));
        onView(withId(R.id.btnBuscar)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.titulo_textView)).check(doesNotExist());
    }
}
