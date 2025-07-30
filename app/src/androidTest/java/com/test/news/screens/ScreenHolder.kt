package com.test.news.screens

import androidx.compose.ui.test.junit4.ComposeTestRule

internal class ScreenHolder(
    private val composeTestRule: ComposeTestRule,
) {
    val loginScreen by lazy { LoginScreen(composeTestRule) }
    val newsScreen by lazy { NewsScreen(composeTestRule) }
}
