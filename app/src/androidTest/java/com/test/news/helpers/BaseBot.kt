package com.test.news.helpers

import android.content.Context
import android.os.SystemClock
import androidx.annotation.StringRes
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import dagger.hilt.android.testing.HiltAndroidTest
import timber.log.Timber

@HiltAndroidTest
open class BaseBot(
    private val composeTestRule: ComposeTestRule,
) {
    private val instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }
    internal val uiDevice by lazy { UiDevice.getInstance(instrumentation) }
    private val targetContext: Context by lazy { instrumentation.targetContext }

    private fun waitTillComposeIdle() {
        composeTestRule.waitForIdle()
    }

    private fun <T> waitAndDo(
        waitMillis: Long = 10_000L,
        waitMillisPerTry: Long = 500L,
        action: () -> T,
    ): T? {
        var throwable = Throwable("Could not perform action or assertion statement")
        val millisAtStart = SystemClock.elapsedRealtime()
        var currentMillis = millisAtStart

        while (currentMillis < millisAtStart + waitMillis) {
            try {
                waitTillComposeIdle()
                return action()
            } catch (e: Throwable) {
                Thread.sleep(waitMillisPerTry)
                currentMillis = SystemClock.elapsedRealtime()
                throwable = e
            }
        }

        throw throwable
    }

    fun getString(@StringRes stringRes: Int): String = targetContext.getString(stringRes)

    // Compose actions
    fun typeTextOnNodeWithTag(
        testTag: String,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
        textInput: String,
    ) {
        waitAndDo {
            Timber.i("Trying to type text on node with tag: $testTag | text: $textInput")
            composeTestRule.onAllNodesWithTag(
                testTag,
                useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
                .performTextInput(textInput)
        }
    }

    fun tapOnNodeWithTag(
        testTag: String,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
    ) {
        waitAndDo {
            Timber.i("Trying to tap on node with tag: $testTag")
            composeTestRule.onAllNodesWithTag(
                testTag,
                useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
                .performClick()
        }
    }

    // Compose assertions
    fun assertNodeWithStringResIsVisible(
        @StringRes stringRes: Int,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
    ) {
        val text = getString(stringRes)
        waitAndDo {
            Timber.i("Trying to check node with stringRes: $stringRes | $text is visible")
            composeTestRule.onAllNodes(
                hasText(text),
                useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
                .assertExists()
        }
    }

    fun assertNodeWithTagIsVisible(
        testTag: String,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
    ) {
        waitAndDo {
            Timber.i("Trying to check node with tag: $testTag is visible")
            composeTestRule.onAllNodesWithTag(
                testTag,
                useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
                .assertExists()
        }
    }

    fun assertNodeWithTagContainsStringIsVisible(
        testTag: String,
        @StringRes stringRes: Int,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
    ) {
        val text = getString(stringRes)
        waitAndDo {
            Timber.i("Trying to check node with tag: $testTag contains stringRes: $stringRes | text: $text is visible")
            composeTestRule.onAllNodesWithTag(
                testTag,
                useUnmergedTree = useUnmergedTree,
            )[nodeIndex]
                .assert(hasText(text))
        }
    }

    // UiAutomator actions
    fun navigateHome() {
        Timber.i("Navigating to home screen")
        uiDevice.pressHome()
        SystemClock.sleep(500) // Wait for the home screen to load
        uiDevice.waitForIdle()
    }

    fun openRecentApps() {
        Timber.i("Opening recent apps")
        uiDevice.pressRecentApps()
        SystemClock.sleep(500) // Wait for the recent apps to load
        uiDevice.waitForIdle()
        uiDevice.click(uiDevice.displayWidth / 2, uiDevice.displayHeight / 2) // Click on the center of the screen
    }
}
