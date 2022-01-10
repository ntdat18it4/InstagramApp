package com.example.instagramapp

import android.app.Application
import com.example.instagramapp.di.databaseModule
import com.example.instagramapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(databaseModule, viewModelModule)
        }
    }
}