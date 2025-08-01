package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.test.news.helpers.BaseBot

class NewsDetailScreen(
    composeTestRule: ComposeTestRule,
) : BaseBot(composeTestRule) {
    fun assertArticleTitleIsDisplayed(title: String) {
        acceptGoogleTermsBrowser()
        assertChromeWithTextIsDisplayed(title)
    }
}
