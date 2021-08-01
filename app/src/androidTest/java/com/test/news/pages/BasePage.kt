package com.test.news.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BasePage {

    fun enterText(viewId: Int, text: String) {
        onView(withId(viewId))
            .perform(
                typeText(text)
            )
    }
}