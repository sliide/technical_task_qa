package com.test.news.extension

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.test.news.NewsApp

fun Int.asString() = getApplicationContext<NewsApp>().resources.getString(this)
