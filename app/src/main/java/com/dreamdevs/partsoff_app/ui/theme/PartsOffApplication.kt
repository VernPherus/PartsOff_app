package com.dreamdevs.partsoff_app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class PartsOffApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
