package com.test.news.features.login.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.news.features.login.domain.model.LoginResult
import com.test.news.features.login.domain.repository.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Idle())
    val loginResult: StateFlow<LoginResult> = _loginResult

    fun login(
        username: String,
        password: String,
    ) {
        loginInteractor
            .loginWithCredentials(username, password)
            .onEach { _loginResult.value = it }
            .launchIn(viewModelScope)
    }
}
