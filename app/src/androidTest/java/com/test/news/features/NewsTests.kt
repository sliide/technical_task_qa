package com.test.news.features

import com.test.news.hooks.BeforeAfterSetup
import com.test.news.screens.ScreenHolder
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class NewsTests : BeforeAfterSetup() {
    private val screens = ScreenHolder(composeTestRule)
}
