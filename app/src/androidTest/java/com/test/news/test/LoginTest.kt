package com.test.news.test

import com.test.news.R
import com.test.news.base.BaseTest
import com.test.news.extension.asString
import com.test.news.extension.hasErrorText
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.screen.LoginScreen
import com.test.news.screen.NewsScreen
import com.test.news.test.TestData.INVALID_USER_NAME
import com.test.news.test.TestData.INVALID_USER_PASSWORD
import com.test.news.test.TestData.VALID_USER_NAME
import com.test.news.test.TestData.VALID_USER_PASSWORD
import org.junit.Test

class LoginTest : BaseTest<LoginActivity>(LoginActivity::class.java) {

    @Test
    fun log_in_screen_displayed() {
        run {
            step("Given the app is installed") {}
            step("When the user opens the app") {}
            step("Then log-in screen is displayed") {
                LoginScreen {
                    userNameInput {
                        isDisplayed()
                    }
                    passwordInput {
                        isDisplayed()
                    }
                    logInBtn {
                        isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    fun invalid_user_name_login() {
        run {
            step("Given the user provides wrong user name") {
                LoginScreen {
                    enterCredentials(
                        userName = INVALID_USER_NAME,
                        password = VALID_USER_PASSWORD
                    )
                }
            }
            step("When the user clicks login button") {
                LoginScreen {
                    logInBtn {
                        click()
                    }
                }
            }
            step("Then error markers are displayed by user name entry") {
                LoginScreen {
                    userNameInput {
                        hasErrorText(R.string.login_wrong_user_name_error.asString())
                    }
                }
            }
            step("And the user stays on log in screen") {
                LoginScreen {
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun invalid_password_login() {
        run {
            step("Given the user provides wrong password") {
                LoginScreen {
                    enterCredentials(
                        userName = VALID_USER_NAME,
                        password = INVALID_USER_PASSWORD
                    )
                }
            }
            step("When the user clicks login button") {
                LoginScreen {
                    logInBtn {
                        click()
                    }
                }
            }
            step("Then error markers are displayed by password entry") {
                LoginScreen {
                    passwordInput {
                        hasErrorText(R.string.login_wrong_password_error.asString())
                    }
                }
            }
            step("And the user stays on log in screen") {
                LoginScreen {
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun valid_credentials_login() {
        run {
            step("Given the user provides valid credentials") {
                LoginScreen {
                    enterCredentials(
                        userName = VALID_USER_NAME,
                        password = VALID_USER_PASSWORD
                    )
                }
            }
            step("When the user clicks login button") {
                LoginScreen {
                    logInBtn {
                        click()
                    }
                }
            }
            step("Then the news screen is displayed") {
                NewsScreen {
                    isDisplayed()
                }
            }
        }
    }

    @Test
    fun news_screen_landing_for_logged_in_user() {
        run {
            step("Given the user is logged in") {
                LoginScreen {
                    logIn(
                        userName = VALID_USER_NAME,
                        password = VALID_USER_PASSWORD
                    )
                }
                NewsScreen {
                    isDisplayed()
                }
            }
            step("When the user resets the app") {
                resetApp()
            }
            step("Then the news screen is displayed") {
                NewsScreen {
                    isDisplayed()
                }
            }
        }
    }
}
