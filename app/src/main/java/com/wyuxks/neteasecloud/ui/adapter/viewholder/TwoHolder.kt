package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class TwoHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<Int>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: Int, position: Int) {
        val one_imageView = findView<ImageView>(R.id.iv_two_one_one)
        val two_imageView = findView<ImageView>(R.id.iv_two_one_two)
        val one_title = findView<TextView>(R.id.tv_two_one_one_title)
        val two_title = findView<TextView>(R.id.tv_two_one_two_title)

        one_imageView.setOnClickListener({
            toast(it.context, "two_one pic!")
        })
        two_imageView.setOnClickListener({
            toast(it.context, "two_two pic!")
        })
    }

}