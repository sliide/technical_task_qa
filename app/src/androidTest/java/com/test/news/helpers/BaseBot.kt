package com.test.news.helpers

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import dagger.hilt.android.testing.HiltAndroidTest

@HiltAndroidTest
open class BaseBot(private val composeTestRule: ComposeTestRule) {
    private val instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }
    internal val uiDevice by lazy { UiDevice.getInstance(instrumentation) }
    private val targetContext: Context by lazy {instrumentation.targetContext}

    fun getString(@StringRes stringRes: Int): String {
        return targetContext.getString(stringRes)
    }

    // Compose assertions
    fun assertNodeWithStringRes(
        @StringRes stringRes: Int,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0
    ) {
        val text = getString(stringRes)
        composeTestRule.onAllNodes(
            hasText(text),
            useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
            .assertExists()
    }
}
