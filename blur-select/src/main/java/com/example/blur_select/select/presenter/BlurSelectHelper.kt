package com.example.blur_select.select.presenter

import android.content.Context
import android.graphics.Bitmap
import androidx.core.view.drawToBitmap
import com.example.blur_select.blur.Blur
import com.example.blur_select.select.Utils
import com.example.blur_select.select.extansions.dp

class BlurSelectHelper(private val data: BlurSelectData) {

    /**
     * Blur background START
     * */

    fun getBlurredBackgroundBitmap(): Bitmap? {
        data.rootViewRef.get() ?: return null
        return Blur.getInstance().fastBlur(
            data.rootViewRef.get()!!,
            data.config.blurredBgBlurRadius,
            data.config.blurredBgBlurDownScaleFactor
        )
    }

    /**
     * Blur background END
     * */


    /**
     * Select view duplicate START
     * */

    fun getSelectViewBitmap(): Bitmap? {
        data.selectViewRef.get() ?: return null
        return data.selectViewRef.get()!!.drawToBitmap()
    }

    /**
     * Select view duplicate END
     * */

    /**
     * Card START
     * */

    fun getCardTopMargin(
        context: Context?,
        originalYPosition: Int,
        selectViewHeightScaled: Float
    ): Int {
        context ?: return 0
        val marginTopDelta = selectViewHeightScaled + 16.dp + data.config.cardTopAdditionMargin
        val cardHeight = data.config.cardHeight

        val marginTopForBottomPosition = (originalYPosition + marginTopDelta).toInt()
        val marginTopForTopPosition =
            originalYPosition - 16.dp - data.config.cardTopAdditionMargin - cardHeight

        val screenHeight = Utils.getScreenHeightPx(context) ?: return marginTopForBottomPosition

        val useBottomPosition = (cardHeight + marginTopForBottomPosition) < screenHeight

        // also init pivot, depends on view position
        return if (useBottomPosition) {
            data.card?.pivotY = 0f
            marginTopForBottomPosition
        } else {
            data.card?.pivotY = cardHeight.toFloat()
            marginTopForTopPosition
        }
    }

    fun getCardStartMargin(
        context: Context?,
        originalXPosition: Int,
        selectViewWidth: Int,
        selectViewWidthScaled: Float
    ): Int {
        context ?: return 0

        val cardWidth = data.config.cardWidth
        val screenWidth = Utils.getScreenWidthPx(context) ?: return 0

        // start of card to start of select view
        val cardStartToStartOfViewMargin =
            (originalXPosition + (selectViewWidth - selectViewWidthScaled)).toInt() + data.config.cardStartEndAdditionMargin
        // end of card to end of screen
        val cardEndToEndOfScreenMargin = screenWidth - cardWidth - 16.dp - data.config.cardStartEndAdditionMargin
        // start of card to start of screen
        val cardStartToStartOfScreenMargin = 16.dp + data.config.cardStartEndAdditionMargin

        // select view end X position
        val selectViewScaledEndXPosition = originalXPosition + selectViewWidthScaled

        return when {
            /** view if full on screen */
            originalXPosition >= 0 && selectViewScaledEndXPosition < screenWidth -> {
                data.card?.pivotX = 0f // start animation from left side
                cardStartToStartOfViewMargin
            }
            /** view left side on screen, right side out of screen */
            originalXPosition >= 0 && selectViewScaledEndXPosition > screenWidth -> {
                return when {
                    /** card can be start to start of select view and also fit on screen */
                    originalXPosition + cardWidth + 16.dp < screenWidth -> { //
                        data.card?.pivotX = 0f // start animation from left side
                        cardStartToStartOfViewMargin
                    }
                    /** card can NOT be start to start of select view and also fit on screen */
                    else -> {
                        data.card?.pivotX = cardWidth.toFloat() // start animation from left side
                        cardEndToEndOfScreenMargin
                    }
                }
            }
            /** view left side out of screen, right side on screen */
            originalXPosition < 0 && selectViewScaledEndXPosition < screenWidth -> {
                data.card?.pivotX = 0f // start animation from left side
                cardStartToStartOfScreenMargin
            }
            else -> cardStartToStartOfScreenMargin
        }
    }
    /**
     * Card END
     * */
}