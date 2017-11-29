package com.wyuxks.neteasecloud.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des :
 */
object ImageUtils{

    /**
     * 加载圆角图
     */
    fun displayCircle(imageView: ImageView, imageUrl: Any) {
        Glide.with(imageView.context)
                .load(imageUrl)
                .transform(GlideCircleTransform(imageView.context))
                .into(imageView)
    }
}