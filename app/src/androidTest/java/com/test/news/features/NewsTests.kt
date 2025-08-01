package com.test.news.features

import com.test.news.hooks.BeforeAfterSetup
import com.test.news.screens.ScreenHolder
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class NewsTests : BeforeAfterSetup() {
    private val screens = ScreenHolder(composeTestRule)
    private var newsCardTitle: String? = ""

    @Test
    fun verifyNewsImagesAreLoaded() {
        with(screens.loginScreen) {
            loginWIthValidUser()
        }

        with(screens.newsScreen) {
            assertNewsScreenIsDisplayed()
            assertNewsImagesAreLoaded()
        }
    }

    /*
    Test fails due to error message not being displayed after logging into app with
    airplane mode turned on.
     */
    @Test
    fun verifyFailedToLoadImages() {
        with(screens.loginScreen) {
            tapAirplaneMode()
            loginWIthValidUser()
        }

        with(screens.newsScreen) {
            try {
                assertNewsScreenIsDisplayed()
                assertErrorMessage()
                tapAirplaneMode()
            } catch (e: AssertionError) {
                tapAirplaneMode()
                throw e
            }
        }
    }

    @Test
    fun verifyNewsCardIsClicked() {
        with(screens.loginScreen) {
            loginWIthValidUser()
        }

        with(screens.newsScreen) {
            assertNewsScreenIsDisplayed()
            newsCardTitle = newsCardTitle()
            clickOnNewsCard()
        }

        with(screens.newsDetailScreen) {
            assertArticleTitleIsDisplayed(newsCardTitle ?: throw Exception("News card title is null"))
        }
    }
}
