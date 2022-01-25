package com.test.news.extension

import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.text.TextViewAssertions

fun TextViewAssertions.hasErrorText(text: String) {
        view.check(
            ViewAssertions.matches(
            ViewMatchers.hasErrorText(text)))
}
