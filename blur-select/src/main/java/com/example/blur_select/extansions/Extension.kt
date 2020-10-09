package com.example.blur_select.extansions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetBehavior

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun <T> List<T?>?.notNullElements(): List<T>? {
    this ?: return null

    val newArr = ArrayList<T>()

    this.forEach {
        if (it != null)
            newArr.add(it)
    }

    return newArr
}

fun Context.getColorCompat(@ColorRes colorResId: Int): Int = ContextCompat.getColor(this, colorResId)

fun TextView.displayHtml(html: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        this.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    else
        this.text = Html.fromHtml(html)
}

fun FragmentActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun log(tag: String, text: String, ex: Throwable? = null) {
//    if (!BuildConfig.SHOW_LOGS) return

    if (ex == null)
        Log.d(tag, text)
    else
        Log.d(tag, text, ex)
}

fun testLog(text: String, ex: Throwable? = null) {
    if (ex == null)
        log(
            "BODIART_TEST",
            text
        )
    else
        log(
            "BODIART_TEST",
            text,
            ex
        )
}

fun Int.getColor(context: Context?): Int? {
    context ?: return null
    return ContextCompat.getColor(context, this)
}

fun ViewPager.addListener(
    onPageScrollStateChanged: ((state: Int) -> Unit)?,
    onPageScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)?,
    onPageSelected: ((position: Int) -> Unit)?
) {
    this.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            onPageScrollStateChanged?.invoke(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            onPageSelected?.invoke(position)
        }
    })
}