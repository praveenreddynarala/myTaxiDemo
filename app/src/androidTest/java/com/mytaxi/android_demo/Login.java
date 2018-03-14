package com.mytaxi.android_demo;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import helpers.TestBase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static helpers.Matchers.childAtPosition;
import static java.lang.Thread.*;

/**
 * Created by praveenrn on 3/13/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Login extends TestBase {

    @Before
    public void setUpActivity(){
        init();
    }

    @Test
    public void cassUser() throws InterruptedException {
        try {
            onView(withId(R.id.edt_username)).check(matches(isFocusable()));
            Log.i("Assertion", "Edit Username test field is focused");
            onView(withId(R.id.edt_username)).perform(typeText("whiteelephant261"), closeSoftKeyboard());
            Log.i("Action", "Entered Username");
            onView(withId(R.id.edt_password)).perform(typeText("video"), closeSoftKeyboard());
            Log.i("Action", "Entered Password");
            onView(withId(R.id.btn_login)).perform(click());
            Log.i("Action", "Pressed Login button");
            sleep(1000);
            onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
            Log.i("Assertion", "Verified Text Search field is exists");
            onView(withId(R.id.textSearch)).perform(click());
            Log.i("Action", "Pressed Text Search Field");
            onView(withId(R.id.textSearch)).perform(typeText("sa"));
            Log.i("Action", "Entered sa into Text Field");
            Thread.sleep(15000);

            onView(withText("Sarah Friedrich"))
                    .inRoot(RootMatchers.isPlatformPopup())
                    .perform(click());
            Log.i("List Action Item", "Selected Sarah Friedrich from List Control");
            Thread.sleep(2000);
            ViewInteraction floatingActionButton = onView(
                    Matchers.allOf(withId(R.id.fab),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    2),
                            isDisplayed()));
            Log.i("Assertion", "Verified floating action Call button to make calls");
            floatingActionButton.perform(click());
            Log.i("Action", "Press call button to make call");
        }catch (Exception e){
            TakeScreenshot("Failure Screenshot", TestBase.getActivity());
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
