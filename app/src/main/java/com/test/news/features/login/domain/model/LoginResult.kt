package com.test.news.features.login.domain.model

import com.test.news.features.login.data.model.User

sealed class LoginResult {
    data class LoginFailed(
        @LoginFailReason val failReason: Int,
    ) : LoginResult()

    data class LoginSucceed(
        val user: User,
    ) : LoginResult()

    class Idle : LoginResult()
}
