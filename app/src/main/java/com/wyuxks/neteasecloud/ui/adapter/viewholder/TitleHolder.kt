package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.DensityUtil
import com.wyuxks.neteasecloud.utils.ImageUtils

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class TitleHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<Int>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: Int, position: Int) {
        val more = findView<LinearLayout>(R.id.ll_title_more)
        more.setOnClickListener({
            toast(it.context, "更多!")
        })
//
    }

}