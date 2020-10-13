package com.example.blur

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.databinding.ObservableFloat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blur.databinding.ActivityMainBinding
import com.example.blur_select.BlurSelect
import com.example.blur_select.presenter.BlurConfig
import com.example.extansions.setMarginTop
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val towns = arrayListOf("London", "Kyiv", "Lutsk", "Kovel", "Odessa", "Volodymyr", "Kharkiv", "New Jersey", "New York")
    private var adapter = Adapter(towns)


    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        mBinding.presenter = this
        setContentView(mBinding.root)
        super.onCreate(savedInstanceState)

        setupTransparentStatusBar()
        initListView()
    }

    private fun setupTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                statusBarColor = Color.TRANSPARENT
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout)) { _, insets ->
            val statusBarHeight = insets.systemWindowInsetTop
            rv.setMarginTop(statusBarHeight)
            insets.consumeSystemWindowInsets()
        }
    }

    private fun initListView() {
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = adapter

        val viewForCard = layoutInflater.inflate(R.layout.el_card, null, false)

        adapter.setOnItemClickListener { adapter, view, position ->
            createBlurSelect(view, viewForCard, adapter.getItem(position) as String)
//            true
        }
    }

    private fun createBlurSelect(selectView: View, viewForCard: View, item: String) {
        val config = BlurConfig().apply {
            selectViewAnimValueScaleDownTo = 1f
            selectViewAnimDurationScaleDown = 0
            selectViewAnimValueScaleUpTo = 1.04f
        }
        BlurSelect.selectView(this, selectView, viewForCard, config = config).apply {
            addCardListener(R.id.edit) {
                BlurSelect.discard()
            }
            addCardListener(R.id.delete) {
                BlurSelect.discard {
                    adapter.remove(item)
                }
            }
        }
    }
}