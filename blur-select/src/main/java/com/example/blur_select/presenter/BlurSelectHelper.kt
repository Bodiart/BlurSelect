package com.example.blur_select.presenter

import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import io.alterac.blurkit.BlurKit

class BlurSelectHelper(private val data: BlurSelectData) {

    /**
     * Blur background START
     * */

    fun getBlurredBackgroundBitmap(): Bitmap? {
        data.rootViewRef.get() ?: return null
        return BlurKit.getInstance().fastBlur(data.rootViewRef.get(), 14, 0.2f)
    }

    /**
     * Blur background END
     * */


    /**
     * Select view duplicate START
     * */

    fun getSelectViewBitmap(): Bitmap? {
        data.selectViewRef.get() ?: return null
        return data.selectViewRef.get()!!.drawToBitmap()
    }

    /**
     * Select view duplicate END
     * */
}