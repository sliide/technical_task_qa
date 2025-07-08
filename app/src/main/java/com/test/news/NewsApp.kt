package com.test.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

abstract class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        this.initToolsForDebugOnly()
    }

    private fun initToolsForDebugOnly() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

@HiltAndroidApp
class NewsApp : BaseApp()
