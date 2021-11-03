package com.test.news;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.news.features.login.presentation.LoginActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginScenarioTest {

    private static final String INVALID_USER_NAME = "invalid";
    private static final String INVALID_PASSWORD = "invalid";
    private static final String VALID_USER_NAME = "user";
    private static final String VALID_PASSWORD = "password";

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testLoginViewDisplayOnOpen() {
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()));
    }
    
    @Test
    public void testLoginFailedScenario() {
        onView(withId(R.id.editTextUserName))
                .perform(clearText(), typeText(VALID_USER_NAME));
        onView(withId(R.id.editTextPassword))
                .perform(clearText(), typeText(INVALID_PASSWORD));
        onView(withId(R.id.buttonLogin))
                .perform(click());
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText("Wrong password")));

        onView(withId(R.id.editTextUserName))
                .perform(clearText(), typeText(INVALID_USER_NAME));
        onView(withId(R.id.editTextPassword))
                .perform(clearText(), typeText(INVALID_PASSWORD));
        onView(withId(R.id.buttonLogin))
                .perform(click());
        onView(withId(R.id.editTextUserName)).check(matches(hasErrorText("Wrong user name")));
    }

    @Test
    public void testLoginSuccessScenario() {
        onView(withId(R.id.editTextUserName))
                .perform(clearText(), typeText(VALID_USER_NAME));
        onView(withId(R.id.editTextPassword))
                .perform(clearText(), typeText(VALID_PASSWORD));
        onView(withId(R.id.buttonLogin))
                .perform(click());
        onView(withId(R.id.buttonLogin)).check(doesNotExist());
    }

    public void testCloseAndOpenScenario() {
        onView(withId(R.id.editTextUserName))
                .perform(clearText(), typeText(VALID_USER_NAME));
        onView(withId(R.id.editTextPassword))
                .perform(clearText(), typeText(VALID_PASSWORD));
        onView(withId(R.id.buttonLogin))
                .perform(click());
        pressBackUnconditionally();
        loginActivityActivityTestRule.finishActivity();
        loginActivityActivityTestRule.launchActivity(new Intent());
        onView(withId(R.id.buttonLogin)).check(doesNotExist());
    }

}
