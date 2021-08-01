package com.test.news.tests

import android.content.Intent
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import com.test.news.utils.Constants.VALID_USER_NAME
import com.test.news.utils.Constants.VALID_USER_PASSWORD
import org.junit.Test

class NewsScreenTest : BaseTest() {

    private var indexOfBottomElement = 5

    @Test
    fun shouldOpenBrowserAfterSelectingElement() {
        // Given
        loginPage
            .enterUsername(VALID_USER_NAME)
            .enterPassword(VALID_USER_PASSWORD)
            .clickLoginButton()

        // When
        newsPage.assertNewsDisplayed()
        newsPage.clickElementOnList(indexOfBottomElement)

        // Then
        intended(hasAction(Intent.ACTION_VIEW))

        // TODO compare URL
        // TODO this is flaky, not clicking on element each time
    }

}

