package com.example.blur_select.select.presenter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.blur_select.select.Utils.Companion.getLayoutParams
import com.example.blur_select.select.Utils.Companion.getLayoutParamsMatchParent
import com.example.blur_select.select.Utils.Companion.getViewSizes
import com.example.extansions.setMargins

class BlurSelectPresenter(context: Context, selectView: View, viewForCard: View) {

    val data = BlurSelectData(context, selectView, getSelectViewRoot(selectView), viewForCard)
    private val helper = BlurSelectHelper(data)
    private val anim = BlurSelectAnim(data)

    var discardDoneCallback: (() -> Unit)? = null


    internal fun select() {
        // make duplicate like select view
        duplicateSelectView(
            selectViewReplaced = {
                // blur background (select view if already hidden)
                blurBackground()
            },
            duplicateScaleDownEnd = {
                // showInfoCard
                showInfoCard()
            }
        )
    }

    internal fun discard() {
        anim.selectViewDuplicateOff()
        // hide card with anim
        anim.hideCard {
            // remove card from root
            getRootView()?.removeView(data.card)
        }
        // hide blur
        anim.hideBlurredBackground { // end
            // remove blur image view from root view
            getRootView()?.removeView(data.blurredBgImageView)
            // replace select view duplicate with original
            hideSelectViewDuplicate()
            showSelectView()
            // remove select view duplicate
            getRootView()?.removeView(data.selectViewDuplicateCardView)

            data.blurredBgImageView = null
            data.selectViewDuplicateCardView = null
            data.selectViewDuplicateImageView = null
            data.card = null

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

    private fun showSelectView() {
        getSelectView()?.alpha = 1f
    }

    private fun hideSelectView() {
        getSelectView()?.alpha = 0f
    }

    private fun showSelectViewDuplicate() {
        data.selectViewDuplicateCardView?.alpha = 1f
    }

    private fun hideSelectViewDuplicate() {
        data.selectViewDuplicateCardView?.alpha = 0f
    }

    private fun blurBackground() {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return

        // before getting bg blur bitmap - make select view invisible
//        hideSelectView() // select view already hidden
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
            rootView.childCount - 1,
            getLayoutParamsMatchParent()
        )
        // animate blurred bg image view
        anim.showBlurredBackground()
    }


    /**
     * Duplicate Select View START
     * */
    private fun duplicateSelectView(
        selectViewReplaced: () -> Unit,
        duplicateScaleDownEnd: () -> Unit
    ) {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return

        // get select view bitmap
        val selectViewBitmap = helper.getSelectViewBitmap() ?: return
        // create select view duplicate image view
        data.selectViewDuplicateImageView = ImageView(context)
        data.selectViewDuplicateImageView!!.setImageBitmap(selectViewBitmap)
        // create select view duplicate card view
        data.selectViewDuplicateCardView = CardView(context)
        data.selectViewDuplicateCardView!!.cardElevation = 0f
        data.selectViewDuplicateCardView!!.radius = 0f
        data.selectViewDuplicateCardView!!.setContentPadding(0, 0, 0, 0)
        hideSelectViewDuplicate() // hide duplicate at start
        // setup card view if select view is card view
        duplicateSelectViewSetupCardView(selectView)
        // add image view to card view
        data.selectViewDuplicateCardView!!.addView(
            data.selectViewDuplicateImageView!!,
            getLayoutParams(selectViewBitmap.width, selectViewBitmap.height)
        )
        // add select view duplicate image view
        rootView.addView(
            data.selectViewDuplicateCardView!!,
            rootView.childCount,
            getLayoutParams(selectViewBitmap.width, selectViewBitmap.height)
        )
        // setup selected view duplicate margins for actual position
        IntArray(2).let { positions ->
            selectView.getLocationOnScreen(positions)
            data.selectViewDuplicateCardView!!.setMargins(positions[0], positions[1], 0, 0)
        }
        // replace original select view with duplicate
        showSelectViewDuplicate()
        hideSelectView()
        // select view replaced with duplicate - can blur bg
        selectViewReplaced()
        // animate select view duplicate
        anim.selectViewDuplicateOn(duplicateScaleDownEnd)
    }

    private fun duplicateSelectViewSetupCardView(selectView: View) {
        if (selectView is CardView && data.config.selectViewCardDuplicateCardParams) {
            data.selectViewDuplicateCardView!!.cardElevation = selectView.cardElevation
            data.selectViewDuplicateCardView!!.radius = selectView.radius
            data.selectViewDuplicateCardView!!.setCardBackgroundColor(selectView.cardBackgroundColor)
        } else {
            data.selectViewDuplicateCardView!!.radius = data.config.selectViewCardRadius
            data.selectViewDuplicateCardView!!.setCardBackgroundColor(data.config.selectViewCardBackgroundColor)
        }
    }
    /**
     * Duplicate Select View END
     * */


    /**
     * Show info card START
     * */
    private fun showInfoCard() {
        val context = getContext() ?: return
        val rootView = getRootView() ?: return
        val selectView = getSelectView() ?: return
        val viewForCard = getViewForCard() ?: return


        // setup inner view size
        showInfoCardSetupInnerViewSize(context, viewForCard)
        // create card
        showInfoCardCreateCard(context)
        // add card inner view
        showInfoCardAddInnerView(viewForCard)
        // add card to root
        rootView.addView(
            data.card!!,
            rootView.childCount,
            getLayoutParams(data.config.cardWidth, data.config.cardHeight)
        )
        // setup margins fot actual position
        showInfoCardSetupMargins(selectView)
        // animate card
        anim.showCard()
    }

    private fun showInfoCardSetupInnerViewSize(context: Context, viewForCard: View) {
        if (!data.config.cardAutoCalculateInnerViewSize)
            return
        getViewSizes(viewForCard, context)?.let { innerViewSize ->
            if (innerViewSize.width != 0)
                data.config.cardWidth = innerViewSize.width
            if (innerViewSize.height != 0)
                data.config.cardHeight = innerViewSize.height
        }
    }

    private fun showInfoCardCreateCard(context: Context) {
        data.card = CardView(context)
        data.card!!.setCardBackgroundColor(Color.WHITE)
        data.card!!.radius = data.config.cardCornersRadius
        data.card!!.scaleX = 0f
        data.card!!.scaleY = 0f
        data.card!!.alpha = 0f
    }

    private fun showInfoCardAddInnerView(viewForCard: View) {
        if (viewForCard.parent != null)
            (viewForCard.parent as ViewGroup).removeView(viewForCard)
        // add inner view to card
        data.card!!.addView(
            viewForCard,
            getLayoutParamsMatchParent()
        )
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

            val marginTop =
                helper.getCardTopMargin(getContext(), positions[1], selectViewHeightScaled)
            val marginStart = helper.getCardStartMargin(
                getContext(),
                positions[0],
                selectViewWidth,
                selectViewWidthScaled
            )

            data.card!!.setMargins(marginStart, marginTop, 0, 0)
        }
    }
    /**
     * Show info card END
     * */


}