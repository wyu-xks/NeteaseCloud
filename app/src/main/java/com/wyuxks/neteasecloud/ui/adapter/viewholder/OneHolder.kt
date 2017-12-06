package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.ui.activity.WebActivity
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.DensityUtil
import com.wyuxks.neteasecloud.utils.ImageUtils

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class OneHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<EveryDayItemBean>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: EveryDayItemBean, position: Int) {
        val imageView = findView<ImageView>(R.id.iv_one_photo)
        val title = findView<TextView>(R.id.tv_one_photo_title)
        if (t?.title.equals("福利")) {
            Glide.with(imageView.getContext())
                    .load(t?.contentLists.get(0).url)
                    .crossFade(1500)
                    .placeholder(R.drawable.img_two_bi_one)
                    .error(R.drawable.img_two_bi_one)
                    .into(imageView)
        } else {
            ImageUtils.displayRandom(1, 0, 0, imageView)
            title.text = t.contentLists[0].desc
        }

        imageView.setOnClickListener({
            startWebActivity(t?.contentLists[0].url, t?.contentLists[0].desc)
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