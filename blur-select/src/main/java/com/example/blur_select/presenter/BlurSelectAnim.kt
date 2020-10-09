package com.example.blur_select.presenter

import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.blur_select.extansions.blurSelectAnimateAlpha
import com.example.blur_select.extansions.blurSelectAnimateScale
import com.example.blur_select.extansions.testLog

class BlurSelectAnim(private val data: BlurSelectData) {
    private var blurredBgImageViewAnimator: ValueAnimator? = null
    private var selectViewDuplicateAnimator: ValueAnimator? = null
    private var cardScaleAnimator: ValueAnimator? = null
    private var cardAlphaAnimator: ValueAnimator? = null

    fun showBlurredBackground(endCallback: (() -> Unit)? = null) {
        blurredBgImageViewAnimator?.cancel()
        data.blurredBgImageView ?: return
        blurredBgImageViewAnimator = data.blurredBgImageView!!.blurSelectAnimateAlpha(
            data.config.blurredBgAnimValueShowTo,
            data.config.blurredBgAnimDurationShow,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback,
            startDelay = data.config.selectViewAnimDurationScaleDown
        )
    }

    fun hideBlurredBackground(endCallback: (() -> Unit)? = null) {
        blurredBgImageViewAnimator?.cancel()
        data.blurredBgImageView ?: return
        blurredBgImageViewAnimator = data.blurredBgImageView!!.blurSelectAnimateAlpha(
            data.config.blurredBgAnimValueHideTo,
            data.config.blurredBgAnimDurationHide,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    /**
     * Show card START
     * */
    fun showCard(endCallback: (() -> Unit)? = null) {
        showCardScale(endCallback)
        showCardAlpha()
    }

    fun hideCard(endCallback: (() -> Unit)? = null) {
        hideCardScale(endCallback)
        hideCardAlpha()
    }

    private fun showCardScale(endCallback: (() -> Unit)?) {
        cardScaleAnimator?.cancel()
        data.card ?: return
        cardScaleAnimator = data.card!!.blurSelectAnimateScale(
            data.config.cardAnimValueScaleShowFrom,
            data.config.cardAnimValueScaleShowTo,
            data.config.cardAnimDurationScaleShow,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    private fun hideCardScale(endCallback: (() -> Unit)?) {
        cardScaleAnimator?.cancel()
        data.card ?: return
        cardScaleAnimator = data.card!!.blurSelectAnimateScale(
            data.config.cardAnimValueScaleHideTo,
            data.config.cardAnimDurationScaleHide,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    private fun showCardAlpha() {
        cardAlphaAnimator?.cancel()
        data.card ?: return
        cardScaleAnimator = data.card!!.blurSelectAnimateAlpha(
            data.config.cardAnimValueAlphaShowTo,
            data.config.cardAnimDurationAlphaShow,
            interpolator = DecelerateInterpolator()
        )
    }

    private fun hideCardAlpha() {
        cardAlphaAnimator?.cancel()
        data.card ?: return
        cardScaleAnimator = data.card!!.blurSelectAnimateAlpha(
            data.config.cardAnimValueAlphaHideTo,
            data.config.cardAnimDurationAlphaHide,
            interpolator = DecelerateInterpolator()
        )
    }
    /**
     * Show card END
     * */


    /**
     * Animate select view duplicate START
     * */
    fun selectViewDuplicateOn(duplicateScaleDownEnd: () -> Unit, endCallback: (() -> Unit)? = null) {
        selectViewDuplicateOnScaleDown( endCallback = {
            duplicateScaleDownEnd()
            selectViewDuplicateOnScaleUp(endCallback)
        })
    }

    private fun selectViewDuplicateOnScaleDown(endCallback: (() -> Unit)) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicate ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicate!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleDownTo,
            data.config.selectViewAnimDurationScaleDown,
            interpolator = AccelerateInterpolator(),
            endCallback = endCallback
        )
    }

    private fun selectViewDuplicateOnScaleUp(endCallback: (() -> Unit)?) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicate ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicate!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleUpTo,
            data.config.selectViewAnimDurationScaleUp,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    fun selectViewDuplicateScaleOff(endCallback: (() -> Unit)? = null) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicate ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicate!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleOffTo,
            data.config.selectViewAnimDurationScaleOff,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }
    /**
     * Animate select view duplicate END
     * */

}