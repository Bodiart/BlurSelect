package com.example.blur

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blur.databinding.ActivityMainBinding
import com.example.blur_select.BlurSelect
import com.example.blur_select.config.BlurConfig
import com.example.blur_select.config.BlurConfigs
import com.example.extansions.dp
import com.example.extansions.setMarginTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val towns = arrayListOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
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
        }
    }

    private fun createBlurSelect(selectView: View, viewForCard: View, item: String) {
        val cardElevation = (selectView as CardView).cardElevation
        val config = BlurConfigs.onlyScaleUpConfig(1.03f)
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