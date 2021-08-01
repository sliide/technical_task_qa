package com.test.news.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R

class LoginPage : BasePage() {

    fun assertUsernameInputVisible(): LoginPage {
        onView(withId(editTextUserName)).check(matches(isDisplayed()))
        return this
    }

    fun assertPasswordInputVisible(): LoginPage {
        onView(withId(editTextPassword)).check(matches(isDisplayed()))
        return this
    }

    fun assertErrorUsername(): LoginPage {
        onView(withId(editTextUserName))
            .check(matches(hasErrorText("Wrong user name")))
        return this
    }

    fun assertErrorPassword(): LoginPage {
        onView(withId(editTextPassword))
            .check(matches(hasErrorText("Wrong password")))
        return this
    }

    fun assertLoginButtonVisible(): LoginPage {
        onView(withId(buttonLogin)).check(matches(isDisplayed()))
        return this
    }

    fun enterUsername(username: String): LoginPage {
        enterText(editTextUserName, username)
        return this
    }

    fun enterPassword(password: String): LoginPage {
        enterText(editTextPassword, password)
        return this
    }

    fun clickLoginButton(): LoginPage {
        onView(withId(buttonLogin)).perform(click())
        return this
    }

    companion object {
        private const val buttonLogin = R.id.buttonLogin
        private const val editTextUserName = R.id.editTextUserName
        private const val editTextPassword = R.id.editTextPassword
    }
}