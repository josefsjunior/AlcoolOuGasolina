package com.example.josefernandes.convert;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ConvertActivityTest4 {

    @Rule
    public ActivityTestRule<ConvertActivity_> mActivityTestRule = new ActivityTestRule<>(ConvertActivity_.class, false, true);

    @Test
    public void clicarNoBotaoComGasolinaNegativa_apareceMensagemDeErro(){
        onView(withId(R.id.convert_value_gasolina)).perform(typeText(".999"));
        closeSoftKeyboard();
        onView(withId(R.id.convert_value_alcool)).perform(typeText("-2.699"));
        closeSoftKeyboard();
        onView(withId(R.id.convert_button)).perform(click());
        //onView(withText(R.string.abasteca_gasolina)).check(matches(not(isDisplayed())));
        //onView(withText(R.string.abasteca_alcool)).check(matches(not(isDisplayed())));
    }
}
