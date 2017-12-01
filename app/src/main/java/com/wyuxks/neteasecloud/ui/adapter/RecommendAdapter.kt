package com.wyuxks.neteasecloud.ui.adapter

import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.adapter.viewholder.OneHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.ThreeHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.TitleHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.TwoHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewAdapter
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder

/**
 * Created by jingbin on 2016/12/1.
 */

class RecommendAdapter : BaseRecyclerViewAdapter<Int>() {


    private val TYPE_TITLE = 1 // title
    private val TYPE_ONE = 2// 一张图
    private val TYPE_TWO = 3// 二张图
    private val TYPE_THREE = 4// 三张图

    override fun getItemViewType(position: Int): Int {
        if (data.get(position) == 1) {
            return TYPE_TITLE
        } else if (data.get(position) == 2) {
            return TYPE_ONE
        } else if (data.get(position) == 3) {
            return TYPE_TWO
        } else if (data.get(position) == 4) {
            return TYPE_THREE
        }
        return super.getItemViewType(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<Int> {
        when (viewType) {
            TYPE_TITLE -> return TitleHolder(parent, R.layout.item_recommend_title)
            TYPE_ONE -> return OneHolder(parent, R.layout.item_recommend_one)
            TYPE_TWO -> return TwoHolder(parent, R.layout.item_recommend_two)
            TYPE_THREE -> return ThreeHolder(parent, R.layout.item_recommend_three)
            else -> return TitleHolder(parent, R.layout.item_recommend_title)
        }
    }
}