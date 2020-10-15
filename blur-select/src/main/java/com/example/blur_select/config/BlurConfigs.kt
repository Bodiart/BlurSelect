package com.example.blur_select.config

import com.example.blur_select.extansions.dp

/**
 * Class for getting configured blur config
 * */
class BlurConfigs {
    companion object {

        /**
         * Skip scale down (with scale down duration)
         * @param scaleUpTo - can be lesser than 1.0
         * */
        fun onlyScaleUpConfig(scaleUpTo: Float? = null) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo ?: selectViewAnimValueScaleUpTo
        }

        /**
         * Enabled shadow animation also
         * */
        fun withShadowConfig(elevationFrom: Float = 0f, elevationTo: Float = 2.dp.toFloat()) =
            BlurConfig().apply {
                // shadow
                selectViewCardShadowAnimEnabled = true
                // shadow on
                selectViewCardAnimDurationShadowOn = 200
                selectViewCardAnimValueShadowOnFrom = elevationFrom
                selectViewCardAnimValueShadowOnTo = elevationTo
                // shadow off
                selectViewCardAnimDurationShadowOff = 200
                selectViewCardAnimValueShadowOffTo = elevationFrom
            }

        /**
         * Skip scale down (with scale down duration)
         * Enabled shadow animation also
         * */
        fun onlyScaleUpConfigWithShadow(
            scaleUpTo: Float? = null,
            elevationFrom: Float = 0f,
            elevationTo: Float = 2.dp.toFloat()
        ) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo ?: selectViewAnimValueScaleUpTo

            // shadow
            selectViewCardShadowAnimEnabled = true
            // shadow on
            selectViewCardAnimDurationShadowOn = 200
            selectViewCardAnimValueShadowOnFrom = elevationFrom
            selectViewCardAnimValueShadowOnTo = elevationTo
            // shadow off
            selectViewCardAnimDurationShadowOff = 200
            selectViewCardAnimValueShadowOffTo = elevationFrom
        }

    }
}