package com.wyuxks.neteasecloud.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.image.GlideCircleTransform

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des :
 */
object ImageUtils {

    val MOVIES_TYPE = 0
    val GIRL_TYPE = 1
    val BOOK_TYPE = 2

    /**
     * 加载圆角图
     */
    fun displayCircle(imageView: ImageView, imageUrl: Any) {
        Glide.with(imageView.context)
                .load(imageUrl)
                .transform(GlideCircleTransform(imageView.context))
                .into(imageView)
    }

    /**
     * 书籍、妹子图、电影列表图
     * 默认图区别
     */
    fun displayEspImage(url: String, imageView: ImageView, type: Int) {
        Glide.with(imageView.context)
                .load(url)
                .crossFade(500)
                .placeholder(getDefaultPic(type))
                .error(getDefaultPic(type))
                .into(imageView)
    }

    private fun getDefaultPic(type: Int): Int {
        when (type) {
            MOVIES_TYPE// 电影
            -> return R.drawable.img_default_movie
            GIRL_TYPE// 妹子
            -> return R.drawable.img_default_meizi
            BOOK_TYPE// 书籍
            -> return R.drawable.img_default_book
        }
        return R.drawable.img_default_meizi
    }
}