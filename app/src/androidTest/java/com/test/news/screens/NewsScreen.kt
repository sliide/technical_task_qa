package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.test.news.AppTestTags
import com.test.news.helpers.BaseBot

class NewsScreen(
    private val composeTestRule: ComposeTestRule,
) : BaseBot(composeTestRule) {

    fun assertNewsScreenIsDisplayed() {
        assertNodeWithTagIsVisible(AppTestTags.NEWS_COLUMN)
    }
}
