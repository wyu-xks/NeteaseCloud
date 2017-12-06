package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.http.rx.RxBus
import com.wyuxks.neteasecloud.http.rx.RxCodeConstants
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.DensityUtil
import com.wyuxks.neteasecloud.utils.ImageUtils

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class TitleHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<EveryDayItemBean>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: EveryDayItemBean, position: Int) {
        val more = findView<LinearLayout>(R.id.ll_title_more)
        val tv_title_type = findView<TextView>(R.id.tv_title_type)
        val iv_title_type = findView<ImageView>(R.id.iv_title_type)
        tv_title_type.text = t.title
        iv_title_type.setImageResource(when (t.title) {
            "Android" -> R.drawable.home_title_android
            "App" -> R.drawable.home_title_app
            "iOS" -> R.drawable.home_title_ios
            "前端" -> R.drawable.home_title_qian
            "休息视频" -> R.drawable.home_title_movie
            "瞎推荐" -> R.drawable.home_title_xia
            "福利" -> R.drawable.home_title_meizi
            "拓展资源" -> R.drawable.home_title_source
            else -> R.drawable.home_title_android
        })
        more.setOnClickListener({
            RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE, t.title)
        })
//
    }

}