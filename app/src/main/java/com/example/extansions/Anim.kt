package com.example.extansions

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.animation.Animation
import androidx.core.animation.addListener
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData


/**
 * Observable float
 * */
fun ObservableFloat.animate(
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null
): ValueAnimator? {
    val fromValue = this.get()

    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            this@animate.set(value)
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            },
            onStart = {
                startCallback?.invoke()
            }
        )

    }
    animator.start()

    return animator
}

fun ObservableFloat.animate(
    fromValue: Float,
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
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
            this@animate.set(value)
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )
    }
    animator.start()

    return animator
}

/**
 * Observable int
 * */
fun ObservableInt.animate(
    toValue: Int,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Int) -> Unit)? = null,
    endCallback: (() -> Unit)? = null,
    startCallback: (() -> Unit)? = null
): ValueAnimator? {
    val fromValue = this.get()

    val animator = ValueAnimator.ofInt(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            this@animate.set(value)
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            },
            onStart = {
                startCallback?.invoke()
            }
        )

    }
    animator.start()

    return animator
}

fun ObservableInt.animate(
    fromValue: Int,
    toValue: Int,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Int) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
): ValueAnimator? {
    val animator = ValueAnimator.ofInt(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            this@animate.set(value)
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )
    }
    animator.start()

    return animator
}

/**
 * MutableLiveData<Float>
 * */
fun MutableLiveData<Float>.animate(
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
): ValueAnimator? {
    val fromValue = this.value ?: return null

    val animator = ValueAnimator.ofFloat(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            this@animate.value = value
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )

    }
    animator.start()

    return animator
}

fun MutableLiveData<Float>.animate(
    fromValue: Float,
    toValue: Float,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Float) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
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
            this@animate.value = value
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )
    }
    animator.start()

    return animator
}

/**
 * MutableLiveData<Int>
 * */
fun MutableLiveData<Int>.animate(
    toValue: Int,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Int) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
): ValueAnimator? {
    val fromValue = this.value ?: return null

    val animator = ValueAnimator.ofInt(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            this@animate.value = value
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )

    }
    animator.start()

    return animator
}

fun MutableLiveData<Int>.animate(
    fromValue: Int,
    toValue: Int,
    duration: Long,
    interpolator: TimeInterpolator? = null,
    updateCallback: ((value: Int) -> Unit)? = null,
    endCallback: (() -> Unit)? = null
): ValueAnimator? {
    val animator = ValueAnimator.ofInt(fromValue, toValue).apply {
        // duration
        setDuration(duration)
        // interpolator
        if (interpolator != null)
            setInterpolator(interpolator)
        // update listener
        addUpdateListener {  valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            this@animate.value = value
            updateCallback?.invoke(value)
        }
        addListener(
            onEnd = {
                endCallback?.invoke()
            }
        )
    }
    animator.start()

    return animator
}

fun Animation.setListener(
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