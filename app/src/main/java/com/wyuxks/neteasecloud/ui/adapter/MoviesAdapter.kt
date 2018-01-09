package com.wyuxks.neteasecloud.ui.adapter

import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.bean.GankIoDayBean
import com.wyuxks.neteasecloud.bean.movies.SubjectsBean
import com.wyuxks.neteasecloud.ui.adapter.viewholder.*
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewAdapter
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder

/**
 * Created by jingbin on 2016/12/1.
 */

class MoviesAdapter : BaseRecyclerViewAdapter<SubjectsBean>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<SubjectsBean> {
        return MoviesHolder(parent, R.layout.item_movies)
    }


}