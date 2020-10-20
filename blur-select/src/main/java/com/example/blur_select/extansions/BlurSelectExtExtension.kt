package com.example.blur_select.extansions

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

val Int.blurSelectExtDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.blurSelectExtdp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun blurSelectExtLog(tag: String, text: String, ex: Throwable? = null) {
    if (ex == null)
        Log.d(tag, text)
    else
        Log.d(tag, text, ex)
}