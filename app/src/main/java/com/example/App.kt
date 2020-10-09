package com.example

import android.app.Application
import android.content.Context
import io.alterac.blurkit.BlurKit

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        BlurKit.init(this)
    }

    companion object {
        lateinit var context: Context
    }
}