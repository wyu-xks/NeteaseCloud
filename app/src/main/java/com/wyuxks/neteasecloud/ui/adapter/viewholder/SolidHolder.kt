package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.ui.activity.WebActivity
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.ImageUtils

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class SolidHolder(var isAll: Boolean, viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<GankIoDataBean.ResultBean>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: GankIoDataBean.ResultBean, position: Int) {
        val iv_all_welfare = findView<ImageView>(R.id.iv_all_welfare)
        val iv_pic = findView<ImageView>(R.id.iv_android_pic)
        val tv_des = findView<TextView>(R.id.tv_android_des)
        val tv_who = findView<TextView>(R.id.tv_android_who)
        val tv_type = findView<TextView>(R.id.tv_content_type)
        val tv_time = findView<TextView>(R.id.tv_android_time)
        val ll_welfare_other = findView<LinearLayout>(R.id.ll_welfare_other)
        iv_all_welfare.visibility = if (t.type.equals("福利")) View.VISIBLE else View.GONE
        ll_welfare_other.visibility = if (t.type.equals("福利")) View.GONE else View.VISIBLE
        tv_type.visibility = if (t.type.equals("福利") || !isAll) View.GONE else View.VISIBLE
        tv_des.text = t.desc
        tv_who.text = t.who
        tv_time.text = t.publishedAt.substring(0..9)
        tv_type.text = " · " + t.type
        if (t.type.equals("福利")) {
            ImageUtils.displayEspImage(t.url, iv_all_welfare, ImageUtils.GIRL_TYPE)
        }
        if (t.images != null && t.images.size > 0) {
            iv_pic.visibility = View.VISIBLE
            ImageUtils.displayGif(t.images[0], iv_pic)
        } else {
            iv_pic.visibility = View.GONE
        }
        itemView.setOnClickListener({
            startWebActivity(t.url, t.desc)
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