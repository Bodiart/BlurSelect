package com.example.blur

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.extansions.dp
import com.example.extansions.setMargins
import kotlin.random.Random

class Adapter(context: Context, private val arr: ArrayList<Int>): ArrayAdapter<Int>(context, R.layout.list_item, arr) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = arr[position]

        val view = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.list_item, null, false)

        val image = when (item) {
            1 -> R.drawable._1
            2 -> R.drawable._2
            3 -> R.drawable._3
            4 -> R.drawable._4
            5 -> R.drawable._5
            6 -> R.drawable._6
            7 -> R.drawable._7
            else -> R.drawable._1
        }

        view.findViewById<ImageView>(R.id.image)?.setImageResource(image)

        return view
    }
}