package com.example

import android.app.Application
import android.content.Context
import com.example.blur_select.BlurSelect

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        BlurSelect.init(this)
    }

    companion object {
        lateinit var context: Context
    }
}