package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cr.ac.ucr.ecci.eseg.catbi.ui.Administrar.AgregarMaterial;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AgregarMatTest {
    public static final String TEST_STRING_TITULO="La esfera luminosa";
    public static final String TEST_STRING_AUTOR="Cixin Liu";
    public static final String TEST_STRING_CANTIDAD="2";
    public static final String TEST_STRING_YEAR="2019";
    public static final String TEST_STRING_COLECCION="Literatura";
    public static final String TEST_STRING_IDIOMA="Español";
    public static final String TEST_STRING_TIPO="Libro";
    public static final String TEST_STRING_BIBLIOTECA="Facultad de Derecho";
    @Rule
    public ActivityScenarioRule<AgregarMaterial> activityRule =
            new ActivityScenarioRule(AgregarMaterial.class);

    @Test
    public void agregarMaterial() {
        onView(withId(R.id.add_titulo)).perform(typeText(TEST_STRING_TITULO), closeSoftKeyboard());
        onView(withId(R.id.add_autores)).perform(typeText(TEST_STRING_AUTOR), closeSoftKeyboard());
        onView(withId(R.id.add_año)).perform(typeText(TEST_STRING_YEAR), closeSoftKeyboard());
        onView(withId(R.id.add_cantidad)).perform(typeText(TEST_STRING_CANTIDAD), closeSoftKeyboard());
        //onView(withId(R.id.add_coleccion)).perform(typeText(TEST_STRING_COLECCION), closeSoftKeyboard());
        onView(withId(R.id.add_idioma)).perform(typeText(TEST_STRING_IDIOMA), closeSoftKeyboard());
        onView(withId(R.id.add_tipo_mat)).perform(typeText(TEST_STRING_TIPO), closeSoftKeyboard());
        //onView(withId(R.id.add_biblioteca)).perform(typeText(TEST_STRING_BIBLIOTECA), closeSoftKeyboard());
        onView(withId(R.id.btn_add_material)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
    }
}
