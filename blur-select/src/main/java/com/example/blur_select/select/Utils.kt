package com.example.blur_select.select

import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.blur_select.select.entity.ViewSize


class Utils {
    companion object {

        fun getLayoutParamsMatchParent() = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        fun getLayoutParams(width: Int, height: Int) = ViewGroup.LayoutParams(width, height)

        fun getLayoutParamsWrapContent() = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        fun getScreenWidthPx(context: Context): Int? {
            val window = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val display = window?.defaultDisplay
            val size = Point()
            (display ?: return null).getSize(size)
            return size.x
        }

        fun getScreenHeightPx(context: Context): Int? {
            val window = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
            val display = window?.defaultDisplay
            val size = Point()
            (display ?: return null).getSize(size)
            return size.y
        }

        fun getViewSizes(view: View, context: Context): ViewSize? {
            val screenWidth = getScreenWidthPx(context) ?: return null
            val screenHeight = getScreenHeightPx(context) ?: return null
            view.measure(screenWidth, screenHeight)

            return ViewSize(view.measuredWidth, view.measuredHeight)
        }

    }
}