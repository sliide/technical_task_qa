package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.test.news.AppTestTags
import com.test.news.R
import com.test.news.helpers.BaseBot

class LoginScreen(
    private val composeTestRule: ComposeTestRule,
) : BaseBot(composeTestRule) {
    private val userNameHintString = R.string.login_user_name_hint
    private val passwordHintString = R.string.login_password_hint
    private val loginButtonString = R.string.login_button_text
    private val loginErrorMessageUnknown = R.string.login_unknown_error
    private val loginErrorMessageWrongUserName = R.string.login_wrong_user_name_error
    private val loginErrorMessageWrongPassword = R.string.login_wrong_password_error

    fun loginWIthValidUser() {
        assertLoginScreenIsDisplayed()
        enterUsernameAndPassword(
            username = com.test.news.helpers.HardcodedUsers.VALID_USER.userName,
            password = com.test.news.helpers.HardcodedUsers.VALID_USER.password,
        )
        tapLoginButton()
    }

    fun assertLoginScreenIsDisplayed() {
        listOf(
            userNameHintString,
            passwordHintString,
            loginButtonString,
        ).forEach { string -> assertNodeWithStringResIsVisible(string) }
    }

    fun enterUsernameAndPassword(
        username: String,
        password: String,
    ) {
        typeTextOnNodeWithTag(AppTestTags.USER_NAME_TEXT_FIELD, textInput = username)
        typeTextOnNodeWithTag(AppTestTags.PASSWORD_TEXT_FIELD, textInput = password)
        dismissKeyboard()
    }

    fun tapLoginButton() {
        tapOnNodeWithTag(AppTestTags.LOGIN_BUTTON)
    }

    fun assertLoginErrorMessageWithInvalidUserNameAndPasswordIsDisplayed() {
        assertNodeWithTagContainsStringIsVisible(AppTestTags.LOGIN_ERROR_MESSAGE, loginErrorMessageUnknown)
    }

    fun assertLoginErrorMessageWithWrongUserNameIsDisplayed() {
        assertNodeWithTagContainsStringIsVisible(AppTestTags.LOGIN_ERROR_MESSAGE, loginErrorMessageWrongUserName)
    }

    fun assertLoginErrorMessageWithWrongPasswordIsDisplayed() {
        assertNodeWithTagContainsStringIsVisible(AppTestTags.LOGIN_ERROR_MESSAGE, loginErrorMessageWrongPassword)
    }
}
