package com.test.news.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.test.news.R

object LoginScreen : BaseScreen<LoginScreen>() {
    val userNameInput = KEditText { withId(R.id.editTextUserName) }
    override val specificView = userNameInput
    val passwordInput = KEditText { withId(R.id.editTextPassword) }
    val logInBtn = KButton { withId(R.id.buttonLogin) }

    fun enterCredentials(userName: String, password: String) {
        userNameInput.typeText(userName)
        passwordInput.typeText(password)
    }

    fun logIn(userName: String, password: String) {
        enterCredentials(userName, password)
        logInBtn.click()
    }
}
