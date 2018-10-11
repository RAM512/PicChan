package ru.rodionov.aleksey.picchan

import android.app.Application
import android.content.Context
import timber.log.Timber

class PicChanApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
