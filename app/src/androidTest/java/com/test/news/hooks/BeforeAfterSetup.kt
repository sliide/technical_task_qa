package com.test.news.hooks

import android.app.Instrumentation
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.test.news.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import timber.log.Timber

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
open class BeforeAfterSetup {
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()

        executeAdbCommand("settings put global window_animation_scale 0")
        executeAdbCommand("settings put global transition_animation_scale 0")
        executeAdbCommand("settings put global animator_duration_scale 0")
        executeAdbCommand("settings put global heads_up_notifications_enabled 0")
    }

    @After
    fun tearDown() {
        executeAdbCommand("settings put global window_animation_scale 1")
        executeAdbCommand("settings put global transition_animation_scale 1")
        executeAdbCommand("settings put global animator_duration_scale 1")
        executeAdbCommand("settings put global heads_up_notifications_enabled 1")
    }

    private fun executeAdbCommand(command: String) {
        try {
            uiDevice.executeShellCommand(command)
        } catch (e: Exception) {
            Timber.w("Failed to execute ADB command: $command", e)
        }
    }
}
