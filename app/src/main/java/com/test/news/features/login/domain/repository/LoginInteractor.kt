package com.test.news.features.login.domain.repository

import com.test.news.features.login.data.model.User
import com.test.news.features.login.data.repository.UsersRepository
import com.test.news.features.login.domain.model.LoginFailReason
import com.test.news.features.login.domain.model.LoginResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginInteractor
@Inject
constructor(
    private val usersRepository: UsersRepository,
) {
    fun loginWithCredentials(
        userName: String,
        password: String,
    ): Flow<LoginResult> = usersRepository.getUser(userName).map {
        it?.let { getResultForFoundUser(it, password) }
            ?: LoginResult.LoginFailed(LoginFailReason.Companion.WRONG_USER_NAME)
    }

    private fun getResultForFoundUser(
        user: User,
        password: String,
    ) = if (user.password == password) {
        LoginResult.LoginSucceed(user)
    } else {
        LoginResult.LoginFailed(LoginFailReason.Companion.WRONG_PASSWORD)
    }
}
