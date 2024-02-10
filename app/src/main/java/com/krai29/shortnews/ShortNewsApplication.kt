package com.krai29.shortnews

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShortNewsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "")
    }

    companion object{
        const val TAG = "ShortNewsApplication"
    }
}