package com.example.blur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.blur_select.select.BlurSelect
import com.example.blur_select.select.config.BlurConfigs
import com.example.extansions.dp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val towns = arrayListOf(1, 2, 3, 4)
    private var adapter: Adapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        initListView()
    }

    private fun initListView() {
        adapter = Adapter(this, towns)
        listView.adapter = adapter!!
        listView.divider = null
        listView.dividerHeight = 0
        val viewForCard = layoutInflater.inflate(R.layout.el_card, null, false)

        listView.setOnItemClickListener { _, view, position, _ ->
            createBlurSelect(view.findViewById(R.id.list_item_card), viewForCard, position)
        }

        adapter?.notifyDataSetChanged()
    }

    private fun createBlurSelect(selectView: View, viewForCard: View, position: Int) {
        val config = BlurConfigs.onlyScaleUpWithShadowConfig(elevationFrom = 2f.dp, elevationTo = 6f.dp, scaleUpTo = 1.03f)
        BlurSelect.selectView(this, selectView, viewForCard, config = config).apply {
            addCardListener(R.id.edit) {
                BlurSelect.discard()
            }
            addCardListener(R.id.delete) {
                BlurSelect.discard {
                    towns.removeAt(position)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}