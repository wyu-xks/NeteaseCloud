package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.DensityUtil
import com.wyuxks.neteasecloud.utils.ImageUtils
import com.wyuxks.neteasecloud.utils.findViewOften

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class WelfareHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<String>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: String, position: Int) {
        val imageView = findView<ImageView>(R.id.iv_welfare)
        ImageUtils.displayEspImage(t, imageView, ImageUtils.GIRL_TYPE)
        if (position % 2 == 0) {
            DensityUtil.setViewMargin(imageView, false, 12, 6, 12, 0)
        } else {
            DensityUtil.setViewMargin(imageView, false, 6, 12, 12, 0)
        }
        imageView.setOnClickListener({
            listener?.onClick(t,position)
        })
    }

}