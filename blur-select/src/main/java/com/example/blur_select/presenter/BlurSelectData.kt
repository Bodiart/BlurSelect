package com.example.blur_select.presenter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.blur_select.extansions.dp
import java.lang.ref.WeakReference

class BlurSelectData(context: Context, selectView: View, rootView: ViewGroup, viewForCard: View) {

    val contextRef = WeakReference(context)
    val selectViewRef = WeakReference(selectView)
    val rootViewRef = WeakReference(rootView)
    val viewForCardRef = WeakReference(viewForCard)

    var blurredBgImageView: ImageView? = null
    var selectViewDuplicateCardView: CardView? = null
    var selectViewDuplicateImageView: ImageView? = null
    var card: CardView? = null

    // listeners

    // config
    var config = BlurConfig()
}