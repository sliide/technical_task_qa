package com.test.news.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.test.news.R
import com.test.news.features.news.presentation.adapter.NewsWidgetsAdapter
import com.test.news.utils.instructions.RecyclerViewNewsInstruction

class NewsPage : BasePage() {

    fun assertNewsDisplayed(): NewsPage {
        ConditionWatcher.setTimeoutLimit(5000)
        ConditionWatcher.waitForCondition(RecyclerViewNewsInstruction())
        onView(withId(recyclerViewNews)).check(matches(isDisplayed()))
        return this
    }

    fun assertErrorDisplayed(): NewsPage {
        onView(withId(textViewError)).check(matches(isDisplayed()))
        return this
    }

    fun scrollToElement(index: Int): NewsPage {
        onView(withId(recyclerViewNews))
            .perform(
                RecyclerViewActions.scrollToPosition<NewsWidgetsAdapter.ViewHolder>(
                    index
                )
            )
        return this
    }

    fun clickElementOnList(index: Int): NewsPage {
        onView(withId(recyclerViewNews))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )
        return this
    }

    companion object {
        private const val recyclerViewNews = R.id.recyclerViewNews
        private const val textViewError = R.id.textViewError
    }
}