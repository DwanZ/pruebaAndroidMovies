package com.rappi.movies

import android.app.Application
import android.content.Context

import com.facebook.stetho.Stetho
import android.support.multidex.MultiDex



class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
            setupStetho()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun setupStetho() = Stetho.initializeWithDefaults(this)
}
