package com.test.news.hooks

import android.app.Instrumentation
import android.os.SystemClock
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
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
        if (uiDevice.hasObject(By.pkg("com.android.chrome"))) {
            Timber.i("Chrome app is running, killing it")
            killChrome()
        }

        executeAdbCommand("settings put global window_animation_scale 1")
        executeAdbCommand("settings put global transition_animation_scale 1")
        executeAdbCommand("settings put global animator_duration_scale 1")
        executeAdbCommand("settings put global heads_up_notifications_enabled 1")
        executeAdbCommand("settings put global airplane_mode_on 0")
        executeAdbCommand("svc wifi enable")
    }

    fun executeAdbCommand(command: String) {
        try {
            uiDevice.executeShellCommand(command)
        } catch (e: Exception) {
            Timber.w("Failed to execute ADB command: $command", e)
        }
    }

    fun killChrome() {
        Timber.i("Killing Chrome app")
        uiDevice.executeShellCommand("am force-stop com.android.chrome")
        SystemClock.sleep(500) // Wait for the app to close
        uiDevice.waitForIdle()
    }
}
