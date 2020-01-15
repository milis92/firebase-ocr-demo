package com.forestgump.boilerplate

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO Plant production logging tree, for example Crashlytics Exceptions Tree
        }

        AndroidThreeTen.init(this)
    }
}
