package com.example.blur_select.blur

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.View
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


class Blur {

    fun blur(src: Bitmap?, radius: Float): Bitmap? {
        val input = Allocation.createFromBitmap(rs, src)
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(src)
        return src
    }

    fun blur(src: View, radius: Float): Bitmap? {
        val bitmap = getBitmapForView(src)
        return blur(bitmap, radius)
    }

    fun fastBlur(src: View, radius: Float, downscaleFactor: Float): Bitmap? {
        val bitmap = getBitmapForView(src, downscaleFactor)
        return blur(bitmap, radius)
    }

    private fun getBitmapForView(src: View, downscaleFactor: Float): Bitmap {
        val bitmap = Bitmap.createBitmap(
            (src.width * downscaleFactor).toInt(),
            (src.height * downscaleFactor).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val matrix = Matrix()
        matrix.preScale(downscaleFactor, downscaleFactor)
        canvas.setMatrix(matrix)
        src.draw(canvas)
        return bitmap
    }

    private fun getBitmapForView(src: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            src.getWidth(),
            src.getHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        src.draw(canvas)
        return bitmap
    }


    companion object {
        private var instance: Blur? = null
        private var rs: RenderScript? = null


        fun init(context: Context) {
            if (instance != null) {
                return
            }
            instance = Blur()
            rs = RenderScript.create(context.applicationContext)
        }

        fun getInstance(): Blur {
            if (instance == null) {
                throw RuntimeException("Blur not initialized!")
            }
            return instance!!
        }
    }
}