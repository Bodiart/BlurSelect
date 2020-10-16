package com.example.blur_select.select.config

/**
 * Class for getting configured blur config
 * */
class BlurConfigs {
    companion object {

        /**
         * Enabled shadow animation also
         * */
        fun withShadowConfig(elevationFrom: Float = 0f, elevationTo: Float = 8f) =
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

        fun withRadiusConfig(radiusFrom: Float = 0f, radiusTo: Float = 40f) = BlurConfig().apply {
            // radius
            selectViewCardRadiusAnimEnabled = true
            // radius from/to values
            selectViewCardAnimValueRadiusOnFrom = radiusFrom
            selectViewCardAnimValueRadiusOnTo = radiusTo
            selectViewCardAnimValueRadiusOffTo = radiusFrom
            // radius anim duration
            selectViewCardAnimDurationRadiusOn = 200
            selectViewCardAnimDurationRadiusOff = 200
        }

        /**
         * Skip scale down (with scale down duration)
         * @param scaleUpTo - can be lesser than 1.0
         * */
        fun onlyScaleUpConfig(scaleUpTo: Float = 1.05f) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo
        }

        /**
         * Enabled shadow animation and radius animation
         * */
        fun withShadowAndRadiusConfig(
            elevationFrom: Float = 0f,
            elevationTo: Float = 8f,
            radiusFrom: Float = 0f,
            radiusTo: Float = 30f
        ) =
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

                // radius
                selectViewCardRadiusAnimEnabled = true
                // radius from/to values
                selectViewCardAnimValueRadiusOnFrom = radiusFrom
                selectViewCardAnimValueRadiusOnTo = radiusTo
                selectViewCardAnimValueRadiusOffTo = radiusFrom
                // radius anim duration
                selectViewCardAnimDurationRadiusOn = 200
                selectViewCardAnimDurationRadiusOff = 200
            }

        /**
         * Skip scale down (with scale down duration)
         * Enabled shadow animation also
         * */
        fun onlyScaleUpWithShadowConfig(
            scaleUpTo: Float = 1.05f,
            elevationFrom: Float = 0f,
            elevationTo: Float = 20f
        ) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo

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
         * also animate card radius
         * @param scaleUpTo - can be lesser than 1.0
         * */
        fun onlyScaleUpWithRadiusConfig(
            scaleUpTo: Float = 1.05f,
            radiusFrom: Float = 0f,
            radiusTo: Float = 30f
        ) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo

            // radius
            selectViewCardRadiusAnimEnabled = true
            // radius from/to values
            selectViewCardAnimValueRadiusOnFrom = radiusFrom
            selectViewCardAnimValueRadiusOnTo = radiusTo
            selectViewCardAnimValueRadiusOffTo = radiusFrom
            // radius anim duration
            selectViewCardAnimDurationRadiusOn = 200
            selectViewCardAnimDurationRadiusOff = 200
        }

        /**
         * Skip scale down (with scale down duration)
         * Enabled shadow animation also
         * */
        fun onlyScaleUpWithShadowAndRadiusConfig(
            scaleUpTo: Float = 1.05f,
            elevationFrom: Float = 0f,
            elevationTo: Float = 20f,
            radiusFrom: Float = 0f,
            radiusTo: Float = 30f
        ) = BlurConfig().apply {
            // scale down
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleDownTo = 1f
            // scale up
            selectViewAnimValueScaleUpTo = scaleUpTo

            // shadow
            selectViewCardShadowAnimEnabled = true
            // shadow on
            selectViewCardAnimDurationShadowOn = 200
            selectViewCardAnimValueShadowOnFrom = elevationFrom
            selectViewCardAnimValueShadowOnTo = elevationTo
            // shadow off
            selectViewCardAnimDurationShadowOff = 200
            selectViewCardAnimValueShadowOffTo = elevationFrom

            // radius
            selectViewCardRadiusAnimEnabled = true
            // radius from/to values
            selectViewCardAnimValueRadiusOnFrom = radiusFrom
            selectViewCardAnimValueRadiusOnTo = radiusTo
            selectViewCardAnimValueRadiusOffTo = radiusFrom
            // radius anim duration
            selectViewCardAnimDurationRadiusOn = 200
            selectViewCardAnimDurationRadiusOff = 200
        }

    }
}