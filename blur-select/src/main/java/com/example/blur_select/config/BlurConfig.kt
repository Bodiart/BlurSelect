package com.example.blur_select.config

import android.graphics.Color
import android.view.ViewGroup
import com.example.blur_select.extansions.dp

class BlurConfig {

    /**
     * Blurred background START
     * */
    // blur values
    var blurredBgBlurRadius = 14
    var blurredBgBlurDownScaleFactor = 0.2f // bg bitmap downscale (for better performance)
    // anim durations
    var blurredBgAnimDurationShow = 200L // show
    var blurredBgAnimDurationHide = 200L // hide
    // anim values
    var blurredBgAnimValueShowTo = 1f // show to
    var blurredBgAnimValueHideTo = 0f // hide to
    /**
     * Blurred background END
     * */


    /**
     * Card START
     * */
    // sizes
    var cardWidth: Int  = 200.dp // width
    var cardHeight: Int = 180.dp // height
    var cardAutoCalculateInnerViewSize: Boolean = true // auto calculate card inner view size
    var cardCornersRadius = 12.dp.toFloat()
    var cardTopAdditionMargin: Int = 0 // top addition margin
    var cardStartEndAdditionMargin: Int = 0 // start/end addition margin depends of card position
    // anim durations
    var cardAnimDurationScaleShow = 200L // scale show
    var cardAnimDurationScaleHide = 200L // scale hide
    var cardAnimDurationAlphaShow = 150L // alpha show
    var cardAnimDurationAlphaHide = 150L // alpha hide
    // anim values
    var cardAnimValueScaleShowFrom  = 0.2f  // scale show from
    var cardAnimValueScaleShowTo    = 1f    // scale show to
    var cardAnimValueScaleHideTo    = 0.3f  // scale hide to
    var cardAnimValueAlphaShowTo = 1f // alpha show to
    var cardAnimValueAlphaHideTo = 0f // alpha show to
    /**
     * Card END
     * */


    /**
     * Select view START
     * */
    /** image view */
    // anim durations
    var selectViewAnimDurationScaleDown = 150L // scale down
    var selectViewAnimDurationScaleUp   = 200L // scale up
    var selectViewAnimDurationScaleOff  = 200L // scale off
    // anim values
    var selectViewAnimValueScaleDownTo = 0.97f  // scale down to
    var selectViewAnimValueScaleUpTo   = 1.07f  // scale up to
    var selectViewAnimValueScaleOffTo  = 1f  // scale up to
    /** card view */
    // params
    var selectViewCardDuplicateCardParams = true // duplicate select view card view params
    var selectViewCardRadius = 0f // select view duplicate card corner radius (DON'T WORK IF selectViewCardDuplicateCardParams == true)
    var selectViewCardBackgroundColor = Color.WHITE // select view duplicate card bg color (DON'T WORK IF selectViewCardDuplicateCardParams == true)
    var selectViewCardShadowAnimEnabled = false
    // anim durations
    var selectViewCardAnimDurationShadowOn  = 0L // shadow on WARNING!!! : DON'T USE DURATION BIGGER THAN selectViewAnimDurationScaleUp
    var selectViewCardAnimDurationShadowOff = 0L // shadow on WARNING!!! : DON'T USE DURATION BIGGER THAN selectViewAnimDurationScaleOff
    // anim values
    var selectViewCardAnimValueShadowOnFrom = 0f // shadow on from
    var selectViewCardAnimValueShadowOnTo   = 0f  // shadow on to
    var selectViewCardAnimValueShadowOffTo  = 0f  // shadow off to
    /**
     * Select view END
     * */
}