package com.example.blur_select.select

import android.content.Context
import android.util.Log
import android.view.View
import com.example.blur_select.blur.Blur
import com.example.blur_select.select.config.BlurConfig
import com.example.blur_select.select.presenter.BlurSelectPresenter
import java.lang.RuntimeException
import java.lang.ref.WeakReference

class BlurSelect {
    companion object {
        private val TAG = BlurSelect::class.java.simpleName
        private var contextRef: WeakReference<Context>? = null
        private var blurSelectPresenter: BlurSelectPresenter? = null


        fun init(context: Context) {
            Blur.init(context)
            contextRef = WeakReference(context)
        }

        fun selectView(
            selectView: View,
            viewForCard: View,
            discardDoneCallback: (() -> Unit)? = null,
            config: BlurConfig = BlurConfig()
        ): BlurSelectPresenter? {
            val context = contextRef?.get()
            if (context == null) {
                Log.e(
                    TAG,
                    "Context is null",
                    RuntimeException("Context is null. Do you forget to initialize BlurSelect in Application class?")
                )
                return null
            }
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