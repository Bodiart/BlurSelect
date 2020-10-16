package com.example.blur_select.select.presenter

import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.blur_select.select.extansions.blurSelectAnimateAlpha
import com.example.blur_select.select.extansions.blurSelectAnimateRadius
import com.example.blur_select.select.extansions.blurSelectAnimateScale
import com.example.blur_select.select.extansions.blurSelectAnimateShadow

class BlurSelectAnim(private val data: BlurSelectData) {
    private var blurredBgImageViewAnimator: ValueAnimator? = null
    private var selectViewDuplicateAnimator: ValueAnimator? = null
    private var selectViewDuplicateShadowAnimator: ValueAnimator? = null
    private var selectViewDuplicateRadiusAnimator: ValueAnimator? = null
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
            selectViewDuplicateOnShadowOn()
            selectViewDuplicateOnRadiusOn()
            selectViewDuplicateOnScaleUp(endCallback)
        })
    }

    fun selectViewDuplicateOff() {
        selectViewDuplicateScaleOff()
        selectViewDuplicateOffShadowOff()
        selectViewDuplicateOffRadiusOff()
    }

    private fun selectViewDuplicateOnScaleDown(endCallback: (() -> Unit)) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleDownTo,
            data.config.selectViewAnimDurationScaleDown,
            interpolator = AccelerateInterpolator(),
            endCallback = endCallback
        )
    }

    private fun selectViewDuplicateOnScaleUp(endCallback: (() -> Unit)?) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleUpTo,
            data.config.selectViewAnimDurationScaleUp,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    fun selectViewDuplicateScaleOff(endCallback: (() -> Unit)? = null) {
        selectViewDuplicateAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateScale(
            data.config.selectViewAnimValueScaleOffTo,
            data.config.selectViewAnimDurationScaleOff,
            interpolator = DecelerateInterpolator(),
            endCallback = endCallback
        )
    }

    private fun selectViewDuplicateOnShadowOn() {
        if (!data.config.selectViewCardShadowAnimEnabled)
            return
        selectViewDuplicateShadowAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateShadowAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateShadow(
            data.config.selectViewCardAnimValueShadowOnFrom,
            data.config.selectViewCardAnimValueShadowOnTo,
            data.config.selectViewCardAnimDurationShadowOn,
            interpolator = DecelerateInterpolator()
        )
    }

    private fun selectViewDuplicateOffShadowOff() {
        if (!data.config.selectViewCardShadowAnimEnabled)
            return
        selectViewDuplicateShadowAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateShadowAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateShadow(
            data.config.selectViewCardAnimValueShadowOffTo,
            data.config.selectViewCardAnimDurationShadowOff,
            interpolator = DecelerateInterpolator()
        )
    }

    private fun selectViewDuplicateOnRadiusOn() {
        if (!data.config.selectViewCardRadiusAnimEnabled)
            return
        selectViewDuplicateRadiusAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateRadiusAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateRadius(
            data.config.selectViewCardAnimValueRadiusOnFrom,
            data.config.selectViewCardAnimValueRadiusOnTo,
            data.config.selectViewCardAnimDurationRadiusOn,
            interpolator = DecelerateInterpolator()
        )
    }

    private fun selectViewDuplicateOffRadiusOff() {
        if (!data.config.selectViewCardRadiusAnimEnabled)
            return
        selectViewDuplicateRadiusAnimator?.cancel()
        data.selectViewDuplicateCardView ?: return
        selectViewDuplicateRadiusAnimator = data.selectViewDuplicateCardView!!.blurSelectAnimateRadius(
            data.config.selectViewCardAnimValueRadiusOffTo,
            data.config.selectViewCardAnimDurationRadiusOff,
            interpolator = DecelerateInterpolator()
        )
    }
    /**
     * Animate select view duplicate END
     * */

}