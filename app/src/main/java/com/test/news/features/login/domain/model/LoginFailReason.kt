package com.test.news.features.login.domain.model

import androidx.annotation.IntDef

@IntDef(LoginFailReason.Companion.WRONG_USER_NAME, LoginFailReason.Companion.WRONG_PASSWORD)
@Retention(AnnotationRetention.SOURCE)
annotation class LoginFailReason {
    companion object {
        const val WRONG_USER_NAME = 0
        const val WRONG_PASSWORD = 1
    }
}
