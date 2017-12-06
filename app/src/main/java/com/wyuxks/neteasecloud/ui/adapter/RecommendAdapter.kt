package com.wyuxks.neteasecloud.ui.adapter

import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.bean.GankIoDayBean
import com.wyuxks.neteasecloud.bean.ItemBean
import com.wyuxks.neteasecloud.ui.adapter.viewholder.OneHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.ThreeHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.TitleHolder
import com.wyuxks.neteasecloud.ui.adapter.viewholder.TwoHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewAdapter
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.ui.base.baseadpter.OnItemClickListener

/**
 * Created by jingbin on 2016/12/1.
 */

class RecommendAdapter : BaseRecyclerViewAdapter<EveryDayItemBean>() {


    private val TYPE_TITLE = 1 // title
    private val TYPE_ONE = 2// 一张图
    private val TYPE_TWO = 3// 二张图
    private val TYPE_THREE = 4// 三张图
    private val lists = ArrayList<EveryDayItemBean>()


    override fun getItemViewType(position: Int): Int {
        if (data.get(position).contentLists.size == 0) {
            return TYPE_TITLE
        } else if (data.get(position).contentLists.size == 1) {
            return TYPE_ONE
        } else if (data.get(position).contentLists.size == 2) {
            return TYPE_TWO
        } else if (data.get(position).contentLists.size >= 3) {
            return TYPE_THREE
        }
        return super.getItemViewType(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<EveryDayItemBean> {
        when (viewType) {
            TYPE_TITLE -> return TitleHolder(parent, R.layout.item_recommend_title)
            TYPE_ONE -> return OneHolder(parent, R.layout.item_recommend_one)
            TYPE_TWO -> return TwoHolder(parent, R.layout.item_recommend_two)
            TYPE_THREE -> return ThreeHolder(parent, R.layout.item_recommend_three)
            else -> return TitleHolder(parent, R.layout.item_recommend_title)
        }
    }

    fun setData(t: GankIoDayBean?) {
        lists.clear()
        t?.category?.forEach {
            val itbean = EveryDayItemBean(it)
            lists.add(itbean)
            when (it) {
                "Android" -> lists.add(EveryDayItemBean(it, t?.results.Android))
                "App" -> lists.add(EveryDayItemBean(it, t?.results.App))
                "iOS" -> lists.add(EveryDayItemBean(it, t?.results.iOS))
                "前端" -> lists.add(EveryDayItemBean(it, t?.results.前端))
                "休息视频" -> lists.add(EveryDayItemBean(it, t?.results.休息视频))
                "瞎推荐" -> lists.add(EveryDayItemBean(it, t?.results.瞎推荐))
                "福利" -> lists.add(EveryDayItemBean(it, t?.results.福利))
                "拓展资源" -> lists.add(EveryDayItemBean(it, t?.results.拓展资源))
            }

        }
        clear()
        addAll(lists)
        notifyDataSetChanged()
    }

    fun clearData() {
        lists.clear()
    }

}