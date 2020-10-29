package com.example.userapplication

import android.app.Application
import com.example.userapplication.di.mainModule
import org.koin.android.ext.android.startKoin

class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()
     
        egeytytre23tet23w23
        // starting Koin DI
        startKoin(this,
                listOf(mainModule),
                loadPropertiesFromFile = true)
    }
}
