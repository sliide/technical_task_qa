package com.test.news.tests

import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.test.news.features.news.presentation.NewsActivity
import com.test.news.utils.Constants.VALID_USER_NAME
import com.test.news.utils.Constants.VALID_USER_PASSWORD
import com.test.news.utils.Constants.WRONG_USER_NAME
import com.test.news.utils.Constants.WRONG_USER_PASSWORD
import org.junit.Test

class LoginScreenTest : BaseTest() {

    @Test
    fun shouldSeeLoginScreenAfterOpening() {
        // Then
        loginPage
            .assertUsernameInputVisible()
            .assertPasswordInputVisible()
            .assertLoginButtonVisible()
    }

    @Test
    fun shouldDisplayErrorMarkerOnWrongUsername() {
        // Given
        loginPage
            .enterUsername(WRONG_USER_NAME)
            .enterPassword(WRONG_USER_PASSWORD)

        // When
        loginPage.clickLoginButton()

        // Them
        loginPage.assertErrorUsername()
    }

    @Test
    fun shouldDisplayErrorMarkerOnWrongPassword() {
        // Given
        loginPage
            .enterUsername(VALID_USER_NAME)
            .enterPassword(WRONG_USER_PASSWORD)

        // When
        loginPage.clickLoginButton()

        // Them
        loginPage.assertErrorPassword()
    }

    @Test
    fun shouldDisplayErrorMarkerOnEmptyText() {
        // Given
        loginPage.clickLoginButton()

        // Then
        loginPage.assertErrorUsername()
    }

    @Test
    fun shouldOpenNewsScreenOnSuccessfulLogin() {
        // Given
        loginPage
            .enterUsername(VALID_USER_NAME)
            .enterPassword(VALID_USER_PASSWORD)

        // When
        loginPage.clickLoginButton()


        // Then
        intended(hasComponent(NewsActivity::class.java.name))
        newsPage.assertNewsDisplayed()
    }

    @Test
    fun shouldOpenNewsScreenOnLaunchAfterClosingWhenLoggedIn() {
        // Given
        loginPage
            .enterUsername(VALID_USER_NAME)
            .enterPassword(VALID_USER_PASSWORD)
        loginPage.clickLoginButton()

        // When
        pressBackUnconditionally()
        intentsTestRule.finishActivity()
        intentsTestRule.launchActivity(null)

        // Then
        newsPage.assertNewsDisplayed()
    }
}

