package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.ui.activity.WebActivity
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.ImageUtils

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class ThreeHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<EveryDayItemBean>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: EveryDayItemBean, position: Int) {
        val one_imageView = findView<ImageView>(R.id.iv_three_one_one)
        val two_imageView = findView<ImageView>(R.id.iv_three_one_two)
        val three_imageView = findView<ImageView>(R.id.iv_three_one_three)
        val one_title = findView<TextView>(R.id.tv_three_one_one_title)
        val two_title = findView<TextView>(R.id.tv_three_one_two_title)
        val three_title = findView<TextView>(R.id.tv_three_one_three_title)

        ImageUtils.displayRandom(3,0,0,one_imageView)
        ImageUtils.displayRandom(3,1,0,two_imageView)
        ImageUtils.displayRandom(3,1,0,three_imageView)
        one_title.text = t?.contentLists[0].desc
        two_title.text = t?.contentLists[1].desc
        three_title.text = t?.contentLists[2].desc
        one_imageView.setOnClickListener({
            startWebActivity(t?.contentLists[0].url, t?.contentLists[0].desc)
        })
        two_imageView.setOnClickListener({
            startWebActivity(t?.contentLists[1].url, t?.contentLists[1].desc)
        })
        three_imageView.setOnClickListener({
            startWebActivity(t?.contentLists[2].url, t?.contentLists[2].desc)
        })
    }

    fun startWebActivity(url: String, title: String) {
        val instance = NeteaseCloud.instance
        val intent = Intent(instance, WebActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title", title)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        instance.startActivity(intent)
    }

}