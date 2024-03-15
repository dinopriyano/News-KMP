package com.dino.newskmp

import android.app.Application
import com.dino.newskmp.feature.main.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Created by dinopriyano on 05/11/23.
 */

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(baseUrl = "", enableNetworkLogs = true) {
            androidLogger()
            androidContext(this@App)
        }
    }

}
