package com.test.news.screen

import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.screen.Screen

abstract class BaseScreen<out T: Screen<T>> : Screen<T>() {
    abstract val specificView: BaseAssertions
    fun isDisplayed() {
        specificView.isDisplayed()
    }
}
