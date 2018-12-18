package com.joe.st_unitas

import android.app.Application
import com.joe.st_unitas.di.appModules
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}