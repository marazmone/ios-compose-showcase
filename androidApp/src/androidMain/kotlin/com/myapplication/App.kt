package com.myapplication

import android.app.Application
import di.initKoin
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.logger.Level.NONE

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else NONE)
            androidContext(androidContext = this@App)
        }
    }
}