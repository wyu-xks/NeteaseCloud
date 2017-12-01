package com.wyuxks.neteasecloud.ui.base.baseadpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyuxks.neteasecloud.utils.DensityUtil
import com.wyuxks.neteasecloud.utils.findViewOften

/**
 * Created by jingbin on 2016/11/25
 */
open abstract class BaseRecyclerViewHolder<T>(viewGroup: ViewGroup, layoutId: Int) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(layoutId, null)) {


//    init {
//        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        itemView.layoutParams = lp
//    }
    fun <T : View> findView(viewId: Int): T {
        return itemView.findViewOften(viewId)
    }

    fun onBaseBindViewHolder(t: T, position: Int) {

        onBindViewHolder(t, position)
    }

    abstract fun onBindViewHolder(t: T, position: Int)

}
