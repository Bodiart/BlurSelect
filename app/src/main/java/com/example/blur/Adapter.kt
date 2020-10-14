package com.example.blur

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class Adapter(arr: ArrayList<String>): BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item, arr) {

    override fun convert(holder: BaseViewHolder, item: String) {
//        holder.setText(R.id.tv_my, item)
    }
}