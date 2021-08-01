package com.test.news.tests

import com.test.news.utils.Constants.VALID_USER_NAME
import com.test.news.utils.Constants.VALID_USER_PASSWORD
import com.test.news.utils.Utils.setWifiState
import org.junit.After
import org.junit.Test

class OfflineTest : BaseTest() {

    @After
    fun resetWifi() {
        setWifiState(true)
    }

    @Test
    fun shouldOpenNewsScreenOnSuccessfulLogin() {
        setWifiState(false)
        // Given
        loginPage
            .enterUsername(VALID_USER_NAME)
            .enterPassword(VALID_USER_PASSWORD)

        // When
        loginPage.clickLoginButton()

        // Then
        newsPage.assertErrorDisplayed()
        // MISSING RETRY BUTTON
    }

}

