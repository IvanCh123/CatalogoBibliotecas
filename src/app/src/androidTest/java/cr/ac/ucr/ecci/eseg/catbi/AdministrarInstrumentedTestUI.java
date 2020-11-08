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

public class AdministrarInstrumentedTestUI {
    public static final String TEST_STRING_TITULO="Drácula";
    public static final String TEST_STRING_AUTOR="Bram Stoker";
    public static final String TEST_STRING_CANTIDAD="2";
    public static final String TEST_STRING_YEAR="2008";
    public static final String TEST_STRING_COLECCION="Literaria";
    public static final String TEST_STRING_IDIOMA="Español";
    public static final String TEST_STRING_TIPO="Libro";
    public static final String TEST_STRING_BIBLIOTECA="Carlos Monge";

    public static final String TEST_STRING_TITULO_EDITAR="Don Quijote";
    public static final String TEST_STRING_COLECCION_GENERAL="General";
    public static final String TEST_STRING_COLECCION_LITERARIA="Literaria";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule(MainActivity.class);
    @Test
    public void agregarMaterialDracula() throws InterruptedException {
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

    @Test
    public void eliminarMaterialDracula() throws InterruptedException {
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

    @Test
    public void editarColeccionDonQuijoteTest() throws InterruptedException {
        Thread.sleep(3000);
        onView(withId(R.id.editTextTituloFrase)).perform(replaceText(TEST_STRING_TITULO_EDITAR));
        onView(withId(R.id.btnBuscar)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.titulo_textView)).check(matches(withText(TEST_STRING_TITULO_EDITAR)));
        onView(withText(TEST_STRING_TITULO_EDITAR)).perform(click());
        Thread.sleep(3000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Editar")).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.textViewContentColeccionEditor)).perform(click());
        onView(withText(TEST_STRING_COLECCION_GENERAL)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.button_update)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.textViewContentColeccion)).check(matches(withText(TEST_STRING_COLECCION_GENERAL)));
        Thread.sleep(3000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Editar")).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.textViewContentColeccionEditor)).perform(click());
        onView(withText(TEST_STRING_COLECCION_LITERARIA)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.button_update)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.textViewContentColeccion)).check(matches(withText(TEST_STRING_COLECCION_LITERARIA)));
    }
}
