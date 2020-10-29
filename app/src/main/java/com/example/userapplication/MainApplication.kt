package com.example.userapplication

import android.app.Application
import com.example.userapplication.di.mainModule
import org.koin.android.ext.android.startKoin

class MainApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        //HELLO RAKSHITH
        // starting Koin DI
        //HI
        //HELLO
        startKoin(this,
                listOf(mainModule),
                loadPropertiesFromFile = true)
    }
}
