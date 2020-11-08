package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.AgregarMaterial;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AgregarMatTest {
    public static final String TEST_STRING_TITULO="Drácula";
    public static final String TEST_STRING_AUTOR="Bram Stoker";
    public static final String TEST_STRING_CANTIDAD="2";
    public static final String TEST_STRING_YEAR="2008";
    public static final String TEST_STRING_COLECCION="Literaria";
    public static final String TEST_STRING_IDIOMA="Español";
    public static final String TEST_STRING_TIPO="Libro";
    public static final String TEST_STRING_BIBLIOTECA="Carlos Monge";
   @Rule
   public ActivityScenarioRule<MainActivity> activityRule =
           new ActivityScenarioRule(MainActivity.class);
    @Test
    public void agregarMaterial() throws InterruptedException {
        Thread.sleep(5000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Agregar Material")).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.add_titulo)).perform(replaceText(TEST_STRING_TITULO));
        onView(withId(R.id.add_autores)).perform(replaceText(TEST_STRING_AUTOR));
        onView(withId(R.id.add_año)).perform(replaceText(TEST_STRING_YEAR));
        onView(withId(R.id.add_cantidad)).perform(replaceText(TEST_STRING_CANTIDAD));
        onView(withId(R.id.add_coleccion)).perform(click());
        onView(withText(TEST_STRING_COLECCION)).perform(click());
        onView(withId(R.id.add_idioma)).perform(replaceText(TEST_STRING_IDIOMA));
        onView(withId(R.id.add_tipo_mat)).perform(replaceText(TEST_STRING_TIPO));
        onView(withId(R.id.add_biblioteca)).perform(click());
        onView(withText(TEST_STRING_BIBLIOTECA)).perform(click());
        onView(withId(R.id.btn_add_material)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO));
        onView(withId(R.id.btnBuscar)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.titulo_textView)).check(matches(withText(TEST_STRING_TITULO)));;
    }


}
