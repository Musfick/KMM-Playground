package com.musfick.playground.android

import android.app.Application
import com.musfick.playground.initKoin

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}