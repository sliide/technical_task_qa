package com.test.news.utils

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage


object Utils {

    fun getActivityInstance(): Activity {

        lateinit var currentActivity: Activity

        getInstrumentation().runOnMainSync {
            val resumedActivities: Collection<Activity> =
                ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext()) {
                currentActivity = resumedActivities.iterator().next()
            }
        }
        return currentActivity
    }


    fun setWifiState(enable: Boolean){
        val wifi = getActivityInstance().getSystemService(Context.WIFI_SERVICE) as WifiManager?
        wifi!!.isWifiEnabled = enable // true or false to activate/deactivate wifi
    }

}