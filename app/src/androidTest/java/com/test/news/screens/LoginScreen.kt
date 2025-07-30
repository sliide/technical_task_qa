package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.test.news.R
import com.test.news.helpers.BaseBot

class LoginScreen(private val composeTestRule: ComposeTestRule): BaseBot(composeTestRule) {
    private val userNameHintString = R.string.login_user_name_hint
    private val passwordHintString = R.string.login_password_hint
    private val loginButtonString = R.string.login_button_text

    fun assertLoginScreenIsDisplayed() {
        assertNodeWithStringRes(userNameHintString)
        assertNodeWithStringRes(passwordHintString)
        assertNodeWithStringRes(loginButtonString)
    }
}
