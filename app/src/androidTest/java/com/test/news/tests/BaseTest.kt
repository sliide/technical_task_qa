package com.test.news.tests

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.pages.LoginPage
import com.test.news.pages.NewsPage
import org.junit.Rule

open class BaseTest {
    protected var loginPage: LoginPage = LoginPage()
    protected var newsPage: NewsPage = NewsPage()

    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)
}