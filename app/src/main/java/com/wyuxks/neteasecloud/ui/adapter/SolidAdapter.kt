package com.wyuxks.neteasecloud.ui.adapter

import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.ui.adapter.viewholder.SolidHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewAdapter
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder

/**
 * Created by jingbin on 2016/12/1.
 */

class SolidAdapter(var isAll: Boolean) : BaseRecyclerViewAdapter<GankIoDataBean.ResultBean>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<GankIoDataBean.ResultBean> = SolidHolder(isAll, parent, R.layout.item_android)


}