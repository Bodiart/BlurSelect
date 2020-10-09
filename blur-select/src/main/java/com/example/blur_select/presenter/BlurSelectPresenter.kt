package com.example.blur_select.presenter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.blur_select.Utils.Companion.getLayoutParams
import com.example.blur_select.Utils.Companion.getLayoutParamsMatchParent
import com.example.blur_select.Utils.Companion.getLayoutParamsWrapContent
import com.example.blur_select.Utils.Companion.getScreenHeightPx
import com.example.blur_select.Utils.Companion.getScreenWidthPx
import com.example.blur_select.extansions.dp
import com.example.extansions.setMargins
import com.example.extansions.setVisible

class BlurSelectPresenter(context: Context, selectView: View, viewForCard: View) {

    val data = BlurSelectData(context, selectView, getSelectViewRoot(selectView), viewForCard)
    private val helper = BlurSelectHelper(data)
    private val anim = BlurSelectAnim(data)

    var discardDoneCallback: (() -> Unit)? = null


    internal fun select() {
        // blur background
        blurBackground()
        // make duplicate like select view
        duplicateSelectView(
            duplicateScaleDownEnd = {
                // showInfoCard
                showInfoCard()
            }
        )
    }

    internal fun discard() {
        // scale down select view duplicate
        anim.selectViewDuplicateScaleOff()
        // hide card with anim
        anim.hideCard {
            // remove card from root
            getRootView()?.removeView(data.card)
        }
        // hide blur
        anim.hideBlurredBackground {
            // remove blur image view from root view
            getRootView()?.removeView(data.blurredBgImageView)
            // make original select view visible
            getSelectView()?.setVisible(true)
            // remove select view duplicate
            getRootView()?.removeView(data.selectViewDuplicate)

            discardDoneCallback?.invoke()
        }
    }

    fun addCardListener(viewId: Int, actionCallback: (view: View) -> Unit) {
        getViewForCard()?.findViewById<View>(viewId)?.setOnClickListener {
            actionCallback(it)
        }
    }

    /**
     * Help functions
     * */

    private fun getSelectViewRoot(selectView: View): ViewGroup = selectView.rootView as ViewGroup

    private fun getContext() = data.contextRef.get()

    private fun getSelectView() = data.selectViewRef.get()

    private fun getRootView() = data.rootViewRef.get()

    private fun getViewForCard() = data.viewForCardRef.get()

    private fun blurBackground() {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return

        // before getting bg blur bitmap - make select view invisible
        selectView.setVisible(false)
        // get blurred background bitmap
        val blurredBgBitmap = helper.getBlurredBackgroundBitmap() ?: return
        // create blurred bg image view
        data.blurredBgImageView = ImageView(context)
        data.blurredBgImageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        data.blurredBgImageView!!.setImageBitmap(blurredBgBitmap)
        data.blurredBgImageView!!.setOnClickListener { discard() }
        // make blurred bg image view alpha = 0
        data.blurredBgImageView!!.alpha = 0f
        // add blurred bg image view to root
        rootView.addView(
            data.blurredBgImageView!!,
            rootView.childCount,
            getLayoutParamsMatchParent()
        )
        // animate blurred bg image view
        anim.showBlurredBackground()
    }

    private fun duplicateSelectView(duplicateScaleDownEnd: () -> Unit) {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return

        // get select view bitmap
        val selectViewBitmap = helper.getSelectViewBitmap() ?: return
        // create select view duplicate image view
        data.selectViewDuplicate = ImageView(context)
        data.selectViewDuplicate!!.setImageBitmap(selectViewBitmap)
        // add select view duplicate image view
        rootView.addView(
            data.selectViewDuplicate!!,
            rootView.childCount,
            getLayoutParams(selectViewBitmap.width, selectViewBitmap.height)
        )
        // setup selected view duplicate margins for actual position
        IntArray(2).let { positions ->
            selectView.getLocationOnScreen(positions)
            data.selectViewDuplicate!!.setMargins(positions[0], positions[1], 0, 0)
        }
        // replace original select view with duplicate
        selectView.setVisible(false)
        // animate select view duplicate
        anim.selectViewDuplicateOn(duplicateScaleDownEnd)
    }


    /**
     * Show info card START
     * */
    private fun showInfoCard() {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return
        val viewForCard = getViewForCard() ?: return

        // create card
        showInfoCardCreateCard(context)
        // add card inner view
        showInfoCardAddInnerView(viewForCard)
        // add card to root
        rootView.addView(data.card!!, rootView.childCount, getLayoutParamsWrapContent())
        // setup margins fot actual position
        showInfoCardSetupMargins(selectView)
        // animate card
        anim.showCard()
    }

    private fun showInfoCardCreateCard(context: Context) {
        data.card = CardView(context)
        data.card!!.setCardBackgroundColor(Color.WHITE)
        data.card!!.radius = 12.dp.toFloat()
        data.card!!.scaleX = 0f
        data.card!!.scaleY = 0f
        data.card!!.alpha = 0f
    }

    private fun showInfoCardAddInnerView(viewForCard: View) {
        if (viewForCard.parent != null)
            (viewForCard.parent as ViewGroup).removeView(viewForCard)
        data.card!!.addView(viewForCard, getLayoutParams(data.config.cardWidth, data.config.cardHeight))
    }

    private fun showInfoCardSetupMargins(selectView: View) {
        IntArray(2).let { positions ->
            selectView.getLocationOnScreen(positions)

            val selectViewScale = data.config.selectViewAnimValueScaleUpTo
            val scaleForOneSide = (((selectViewScale - 1) / 2) + 1)

            val selectViewWidth = selectView.measuredWidth
            val selectViewHeight = selectView.measuredHeight
            val selectViewWidthScaled = selectView.measuredWidth * scaleForOneSide
            val selectViewHeightScaled = selectView.measuredHeight * scaleForOneSide

//            val marginTop = positions[1]/* - (selectViewHeightScaled - selectViewHeight))*/ + selectViewHeightScaled + data.cardTopAdditionMargin
            val marginTop = showInfoCardSetupTopMargin(positions[1], selectViewHeightScaled)
//            val marginStart = (positions[0] + (selectViewWidth - selectViewWidthScaled)).toInt()
            val marginStart = showInfoCardSetupStartMargin(positions[0], selectViewWidth, selectViewWidthScaled)


            data.card!!.setMargins(marginStart, marginTop, 0, 0)
        }
    }

    private fun showInfoCardSetupTopMargin(originalYPosition: Int, selectViewHeightScaled: Float): Int {
        val context = getContext() ?: return 0
        val marginTopDelta = selectViewHeightScaled + data.config.cardTopAdditionMargin
        val cardHeight = data.config.cardHeight

        val marginTopForBottomPosition = (originalYPosition + marginTopDelta).toInt()
        val marginTopForTopPosition = originalYPosition - data.config.cardTopAdditionMargin - cardHeight

        val screenHeight = getScreenHeightPx(context) ?: return marginTopForBottomPosition

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

    private fun showInfoCardSetupStartMargin(originalXPosition: Int, selectViewWidth: Int, selectViewWidthScaled: Float): Int {
        val context = getContext() ?: return 0

        val cardWidth = data.config.cardWidth
        val marginStartDelta = data.config.cardWidth

        val marginStartForEndPosition = (originalXPosition + (selectViewWidth - selectViewWidthScaled)).toInt()
        val marginStartForStartPosition = marginStartForEndPosition - marginStartDelta

        val screenWidth = getScreenWidthPx(context) ?: return marginStartForEndPosition

        val useEndPosition = (cardWidth + marginStartForEndPosition) < screenWidth

        // also init pivot, depends on view position
        return if (useEndPosition) {
            data.card?.pivotX = 0f
            marginStartForEndPosition
        } else {
            data.card?.pivotX = cardWidth.toFloat()
            marginStartForStartPosition
        }
    }
    /**
     * Show info card END
     * */


    /**
     * Setup
     * */


}