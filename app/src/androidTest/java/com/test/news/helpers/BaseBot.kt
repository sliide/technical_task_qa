package com.test.news.helpers

import android.content.Context
import android.os.SystemClock
import androidx.annotation.StringRes
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.internal.ignoreIoExceptions
import timber.log.Timber

@HiltAndroidTest
open class BaseBot(
    private val composeTestRule: ComposeTestRule,
) {
    private val instrumentation by lazy { InstrumentationRegistry.getInstrumentation() }
    internal val uiDevice by lazy { UiDevice.getInstance(instrumentation) }
    private val targetContext: Context by lazy { instrumentation.targetContext }
    private val chromePackage = "com.android.chrome"

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
                Espresso.onIdle()
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

    fun getStringFromNodeWithTag(
        testTag: String,
        useUnmergedTree: Boolean = false,
    ): String {
        Timber.i("Trying to get string from node with tag: $testTag")
        return composeTestRule.onNodeWithTag(
            testTag,
            useUnmergedTree = useUnmergedTree,
        )
            .fetchSemanticsNode().config[SemanticsProperties.Text][0]
            .text
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
                .assertIsDisplayed()
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
                .assertIsDisplayed()
        }
    }

    fun assertNodeWithTagContainsStringIsVisible(
        testTag: String,
        @StringRes stringRes: Int,
        useUnmergedTree: Boolean = false,
        nodeIndex: Int = 0,
    ) {
        val text = getString(stringRes)
        Timber.i("Trying to check node with tag: $testTag contains stringRes: $stringRes | text: $text is visible")
        composeTestRule.onAllNodes(
            hasTestTag(testTag) and hasText(text),
            useUnmergedTree = useUnmergedTree,
        )[nodeIndex]
            .assert(hasText(text))
            .assertExists()
            .assertIsDisplayed()
    }

    fun assertAllNodesWithTagsAreFocused(
        testTag: String,
        useUnmergedTree: Boolean = false,
    ) {
        waitAndDo {
            Timber.i("Trying to check all nodes with tag: $testTag are focused")
            composeTestRule.onAllNodesWithTag(testTag, useUnmergedTree)
                .fetchSemanticsNodes(atLeastOneRootRequired = true)
                .forEach { node ->
                    waitAndDo {
                        composeTestRule.onNodeWithTag(testTag, useUnmergedTree).assertIsFocused()
                    }
                }
        }
    }

    // Espresso actions
    fun dismissKeyboard() {
        Espresso.closeSoftKeyboard()
        Espresso.onIdle()
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

    fun tapAirplaneMode() {
        uiDevice.openQuickSettings()

        val airplaneMode = uiDevice.findObject(
            UiSelector().descriptionContains("Airplane mode"),
        )
        airplaneMode.click()
        Thread.sleep(500L) // Wait for the action to take effect
        navigateHome()
        openRecentApps()
    }

    // Utils
    fun assertChromeWithTextIsDisplayed(text: String) {
        uiDevice.findObject(
            UiSelector()
                .packageName(chromePackage)
                .textContains(text),
        )
    }

    fun acceptGoogleTermsBrowser() {
        val chromeAcceptTerms = "com.android.chrome:id/terms_accept"
        val chromeDeclineSync = "com.android.chrome:id/negative_button"

        val acceptAndContinueButton = uiDevice.findObject(
            UiSelector()
                .packageName(chromePackage)
                .resourceId(chromeAcceptTerms),
        )
        val declineButton = uiDevice.findObject(
            UiSelector()
                .packageName(chromePackage)
                .resourceId(chromeDeclineSync),
        )

        try {
            when {
                acceptAndContinueButton.isClickable -> {
                    acceptAndContinueButton.click()
                    uiDevice.waitForIdle()
                    declineButton.click()
                }

                declineButton.isClickable -> declineButton.click()
                else -> Timber.i("Failed to complete Google browser terms flow or already completed")
            }
        } catch (e: Exception) {
            ignoreIoExceptions { e.stackTrace }
        }
    }
}
