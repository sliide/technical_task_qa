package com.test.news.features

import com.test.news.helpers.HardcodedUsers
import com.test.news.hooks.BeforeAfterSetup
import com.test.news.screens.ScreenHolder
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class LoginTests : BeforeAfterSetup() {
    private val screens = ScreenHolder(composeTestRule)

    @Test
    fun verifyICanSeeLoginScreen() {
        with(screens.loginScreen) {
            assertLoginScreenIsDisplayed()
        }
    }

    /*
    Test fails due to incorrect error message.
     */
    @Test
    fun verifyICannotLoginWithInvalidUsernameAndPassword() {
        with(screens.loginScreen) {
            assertLoginScreenIsDisplayed()
            enterUsernameAndPassword(HardcodedUsers.INVALID_USER.userName, HardcodedUsers.INVALID_USER.password)
            tapLoginButton()
            assertLoginErrorMessageWithInvalidUserNameAndPasswordIsDisplayed()
        }
    }

    @Test
    fun verifyICannotLoginWithWrongUsername() {
        with(screens.loginScreen) {
            assertLoginScreenIsDisplayed()
            enterUsernameAndPassword(HardcodedUsers.EMPTY_USER.userName, HardcodedUsers.VALID_USER.password)
            tapLoginButton()
            assertLoginErrorMessageWithWrongUserNameIsDisplayed()
        }
    }

    @Test
    fun verifyICannotLoginWithWrongPassword() {
        with(screens.loginScreen) {
            assertLoginScreenIsDisplayed()
            enterUsernameAndPassword(HardcodedUsers.VALID_USER.userName, HardcodedUsers.EMPTY_USER.password)
            tapLoginButton()
            assertLoginErrorMessageWithWrongPasswordIsDisplayed()
        }
    }

    @Test
    fun verifyICanLoginWithValidUser() {
        with(screens.loginScreen) {
            loginWIthValidUser()
        }

        with(screens.newsScreen) {
            assertNewsScreenIsDisplayed()
        }
    }

    @Test
    fun verifyIAmStillLoggedInAfterBackgroundAndForeground() {
        with(screens.loginScreen) {
            loginWIthValidUser()
        }

        with(screens.newsScreen) {
            assertNewsScreenIsDisplayed()
            navigateHome()
            openRecentApps()
            assertNewsScreenIsDisplayed()
        }
    }
}
