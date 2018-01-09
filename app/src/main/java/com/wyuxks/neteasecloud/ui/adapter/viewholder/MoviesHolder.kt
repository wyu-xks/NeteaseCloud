package com.wyuxks.neteasecloud.ui.adapter.viewholder

import android.content.Intent
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.huayuni.kotlinlearn.utils.toast
import com.nineoldandroids.view.ViewHelper
import com.nineoldandroids.view.ViewPropertyAnimator
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.bean.movies.SubjectsBean
import com.wyuxks.neteasecloud.ui.activity.WebActivity
import com.wyuxks.neteasecloud.ui.base.baseadpter.BaseRecyclerViewHolder
import com.wyuxks.neteasecloud.utils.ImageUtils
import com.wyuxks.neteasecloud.utils.StringFormatUtil

/**
 *  Author : xks
 *  Data : 2017/11/30 0030
 *  Des :
 */
class MoviesHolder(viewGroup: ViewGroup, layoutId: Int) : BaseRecyclerViewHolder<SubjectsBean>(viewGroup, layoutId) {
    override fun onBindViewHolder(t: SubjectsBean, position: Int) {
        val iv_movies_photo = findView<ImageView>(R.id.iv_movies_photo)
        val tv_movies_title = findView<TextView>(R.id.tv_movies_title)
        val tv_movies_directors = findView<TextView>(R.id.tv_movies_directors)
        val tv_movies_casts = findView<TextView>(R.id.tv_movies_casts)
        val tv_movies_rating_rate = findView<TextView>(R.id.tv_movies_rating_rate)
        val tv_movies_genres = findView<TextView>(R.id.tv_movies_genres)
        ImageUtils.displayEspImage(t.images.large, iv_movies_photo, ImageUtils.MOVIES_TYPE)
        tv_movies_title.text = t.title
        tv_movies_title.text = t.title
        tv_movies_title.text = t.title
        tv_movies_directors.text = StringFormatUtil.formatName(t.directors)
        tv_movies_casts.text = StringFormatUtil.formatName(t.casts)
        tv_movies_genres.text = "类型：" + StringFormatUtil.formatGenres(t.genres)
        tv_movies_rating_rate.text = "评分：" + t.rating.average
        findView<LinearLayout>(R.id.ll_movies_item).setOnClickListener() {
            toast(NeteaseCloud.instance, "item click!")
        }

        ViewHelper.setScaleX(itemView, 0.8f)
        ViewHelper.setScaleY(itemView, 0.8f)
        ViewPropertyAnimator.animate(itemView).scaleX(1f).setDuration(350).setInterpolator(OvershootInterpolator()).start()
        ViewPropertyAnimator.animate(itemView).scaleY(1f).setDuration(350).setInterpolator(OvershootInterpolator()).start()
    }


}