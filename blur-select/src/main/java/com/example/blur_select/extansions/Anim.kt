package com.example.blur_select.extansions

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation
import androidx.core.animation.addListener
import androidx.lifecycle.MutableLiveData


///**
// * MutableLiveData<Float>
// * */
//fun MutableLiveData<Float>.animate(
//    toValue: Float,
//    duration: Long,
//    interpolator: TimeInterpolator? = null,
//    updateCallback: ((value: Float) -> Unit)? = null,
//    endCallback: (() -> Unit)? = null
//): ValueAnimator? {
//    val fromValue = this.value ?: return null
//
//    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
//        // duration
//        setDuration(duration)
//        // interpolator
//        if (interpolator != null)
//            setInterpolator(interpolator)
//        // update listener
//        addUpdateListener {  valueAnimator ->
//            val value = valueAnimator.animatedValue as Float
//            this@animate.value = value
//            updateCallback?.invoke(value)
//        }
//        addListener(
//            onEnd = {
//                endCallback?.invoke()
//            }
//        )
//
//    }
//    animator.start()
//
//    return animator
//}
//
//fun MutableLiveData<Float>.animate(
//    fromValue: Float,
//    toValue: Float,
//    duration: Long,
//    interpolator: TimeInterpolator? = null,
//    updateCallback: ((value: Float) -> Unit)? = null,
//    endCallback: (() -> Unit)? = null
//): ValueAnimator? {
//    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
//        // duration
//        setDuration(duration)
//        // interpolator
//        if (interpolator != null)
//            setInterpolator(interpolator)
//        // update listener
//        addUpdateListener {  valueAnimator ->
//            val value = valueAnimator.animatedValue as Float
//            this@animate.value = value
//            updateCallback?.invoke(value)
//        }
//        addListener(
//            onEnd = {
//                endCallback?.invoke()
//            }
//        )
//    }
//    animator.start()
//
//    return animator
//}

/**
 * Alpha START
 * */
fun View.blurSelectAnimateAlpha(
    fromValue: Float,
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null,
    startDelay: Long? = null
): ValueAnimator? {
    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // start delay
        if (startDelay != null)
            this.startDelay = startDelay
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            this@blurSelectAnimateAlpha.alpha = value
            updateCallback?.invoke(value)
        }
        addListener(
            onStart = { startCallback?.invoke() },
            onEnd = { endCallback?.invoke() }
        )
    }
    animator.start()

    return animator
}

fun View.blurSelectAnimateAlpha(
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null,
    startDelay: Long? = null
): ValueAnimator? {
    return blurSelectAnimateAlpha(this.alpha, toValue, duration, interpolator, updateCallback, endCallback, startCallback, startDelay)
}
/**
 * Alpha END
 * */


/**
 * Scale START
 * */
fun View.blurSelectAnimateScale(
    fromValue: Float,
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null
): ValueAnimator? {
    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            this@blurSelectAnimateScale.scaleX = value
            this@blurSelectAnimateScale.scaleY = value
            updateCallback?.invoke(value)
        }
        addListener(
            onStart = {
                startCallback?.invoke()
            },
            onEnd = {
                endCallback?.invoke()
            }
        )
    }
    animator.start()

    return animator
}

fun View.blurSelectAnimateScale(
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null
): ValueAnimator? {
    return blurSelectAnimateScale(this.scaleX, toValue, duration, interpolator, updateCallback, endCallback, startCallback)
}
/**
 * Scale END
 * */

fun Animation.blurSelectSetListener(
    startCallback: (() -> Unit)? = null,
    repeatCallback: (() -> Unit)? = null,
    endCallback: (() -> Unit)? = null
) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            repeatCallback?.invoke()
        }

        override fun onAnimationEnd(animation: Animation?) {
            endCallback?.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {
            startCallback?.invoke()
        }
    })
}