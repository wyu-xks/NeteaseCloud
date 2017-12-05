package com.wyuxks.neteasecloud.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.adapter.viewholder.WelfareHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewAdapter
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.OnItemClickListener

/**
 * Created by jingbin on 2016/12/1.
 */

class WelfareAdapter : BaseRecyclerViewAdapter<String> {
    constructor(onItemListener:OnItemClickListener<String>){
        listener = onItemListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<String> = WelfareHolder(parent, R.layout.item_welfare)
}
