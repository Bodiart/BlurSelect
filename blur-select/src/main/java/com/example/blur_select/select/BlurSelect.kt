package com.example.blur_select.select

import android.content.Context
import android.view.View
import com.example.blur_select.blur.Blur
import com.example.blur_select.select.config.BlurConfig
import com.example.blur_select.select.presenter.BlurSelectPresenter

class BlurSelect {
    companion object {

        private var blurSelectPresenter: BlurSelectPresenter? = null


        fun init(context: Context) {
            Blur.init(context)
        }

        fun selectView(
            context: Context,
            selectView: View,
            viewForCard: View,
            discardDoneCallback: (() -> Unit)? = null,
            config: BlurConfig = BlurConfig()
        ): BlurSelectPresenter {
            blurSelectPresenter = BlurSelectPresenter(context, selectView, viewForCard).apply {
                this.discardDoneCallback = discardDoneCallback
                this.data.config = config
            }
            blurSelectPresenter!!.select()
            return blurSelectPresenter!!
        }

        fun discard(discardDoneCallback: (() -> Unit)? = null) {
            blurSelectPresenter?.discardDoneCallback = discardDoneCallback
            blurSelectPresenter?.discard()
            blurSelectPresenter = null
        }
    }
}