package com.test.news;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.test.news.features.login.presentation.LoginActivity;
import com.test.news.features.news.presentation.NewsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class NewsScenarioTest {
    private static final String VALID_USER_NAME = "user";
    private static final String VALID_PASSWORD = "password";

    @Rule
    public ActivityTestRule<NewsActivity> newsActivityActivityTestRule = new ActivityTestRule<>(NewsActivity.class);

    @Before
    public void setUp() {
        SystemClock.sleep(2000);
    }
    @Test
    public void testNewsImageLoaded() {
        onView(withId(R.id.recyclerViewNews)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewNews)).perform(RecyclerViewActions.scrollToPosition(10)).check(matches(isDisplayed()));
    }

    @Test
    public void testInternetConnectionDisabledView() {

    }

    @Test
    public void testClickOnImageWebview() {
//        onView(withId(R.id.recyclerViewNews))
    }
}
