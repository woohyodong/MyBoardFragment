package com.woojjajja.myboardfragment

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Logger
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    companion object{
        lateinit var instance: MyApplication
    }
}