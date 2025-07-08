package com.test.news.features.login.domain.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.news.R
import com.test.news.features.login.domain.model.LoginFailReason
import com.test.news.features.login.domain.model.LoginResult
import com.test.news.features.login.domain.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val loginResult by viewModel.loginResult.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(loginResult) {
        when (loginResult) {
            is LoginResult.LoginSucceed -> {
                onLoginSuccess()
                errorMessage = null
            }

            is LoginResult.LoginFailed -> {
                errorMessage =
                    when ((loginResult as LoginResult.LoginFailed).failReason) {
                        LoginFailReason.WRONG_USER_NAME -> context.getString(R.string.login_wrong_user_name_error)
                        LoginFailReason.WRONG_PASSWORD -> context.getString(R.string.login_wrong_password_error)
                        else -> context.getString(R.string.login_unknown_error)
                    }
            }

            else -> {
                errorMessage = null
                keyboardController?.hide()
            }
        }
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp),
        )
        Surface(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 4.dp,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(R.string.login_user_name_hint)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.login_password_hint)) },
                    singleLine = true,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                )
                Button(
                    onClick = {
                        keyboardController?.hide()
                        viewModel.login(username, password)
                    },
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    Text(stringResource(R.string.login_button_text))
                }

                errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
        }
    }
}
