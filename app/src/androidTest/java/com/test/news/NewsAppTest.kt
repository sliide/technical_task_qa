package com.test.news

import com.test.news.hooks.BeforeAfterSetup
import com.test.news.screens.ScreenHolder
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class NewsAppTest : BeforeAfterSetup() {
    private val screens = ScreenHolder(composeTestRule)

    @Test
    fun verifyICanSeeLoginScreen() {
        with(screens.loginScreen) {
            assertLoginScreenIsDisplayed()
        }
    }
}
