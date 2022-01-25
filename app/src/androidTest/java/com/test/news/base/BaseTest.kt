package com.test.news.base

import android.app.Activity
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

open class BaseTest<T : Activity>(activityClass: Class<T>) {

    val activityTestRule = ActivityTestRule(activityClass)

    val testCaseRule = TestCaseRule(
        testClassName = javaClass.simpleName,
        kaspressoBuilder = Kaspresso.Builder.default().apply {
            flakySafetyParams.timeoutMs = TIMEOUT
        }
    )

    @get:Rule
    val rules = RuleChain.outerRule(activityTestRule).around(testCaseRule)

    fun run(steps: TestContext<Unit>.() -> Unit) {
        testCaseRule
            .before {}
            .after {}
            .run(steps)
    }

    fun TestContext<Unit>.resetApp() {
        device.activities.getResumed()?.run {
            finish()
            assert(isFinishing)
        }
        activityTestRule.launchActivity(null)
    }

    private companion object {
        private const val TIMEOUT = 20_000L
    }
}
