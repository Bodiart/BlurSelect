package com.example.blur

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlin.random.Random

class Adapter(arr: ArrayList<String>): BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item, arr) {

    override fun convert(holder: BaseViewHolder, item: String) {
        val random = Random.nextInt(7)
        val image = when (random) {
            1 -> R.drawable._1
            2 -> R.drawable._2
            3 -> R.drawable._3
            4 -> R.drawable._4
            5 -> R.drawable._5
            6 -> R.drawable._6
            7 -> R.drawable._7
            else -> R.drawable._1
        }
        holder.setImageResource(R.id.image, image)
    }
}