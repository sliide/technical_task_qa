package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.test.news.AppTestTags
import com.test.news.R
import com.test.news.helpers.BaseBot

class NewsScreen(
    private val composeTestRule: ComposeTestRule,
) : BaseBot(composeTestRule) {
    private val failedToLoadMessage = R.string.news_failed_to_load_message

    fun assertNewsScreenIsDisplayed() {
        assertNodeWithTagIsVisible(AppTestTags.NEWS_COLUMN)
    }

    fun assertNewsImagesAreLoaded() {
        assertAllNodesWithTagsAreFocused(AppTestTags.NEWS_CARD_IMAGE)
    }

    fun assertErrorMessage() {
        assertNodeWithTagContainsStringIsVisible(AppTestTags.NEWS_ERROR_MESSAGE, failedToLoadMessage)
    }

    fun clickOnNewsCard() {
        tapOnNodeWithTag(AppTestTags.NEWS_CARD_TITLE, useUnmergedTree = true, nodeIndex = 0)
    }

    fun newsCardTitle(): String = getStringFromNodeWithTag(AppTestTags.NEWS_CARD_TITLE, useUnmergedTree = true)
}
