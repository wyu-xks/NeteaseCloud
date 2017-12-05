package com.wyuxks.neteasecloud.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.image.GlideCircleTransform
import java.util.*

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des :
 */
object ImageUtils {

    val MOVIES_TYPE = 0
    val GIRL_TYPE = 1
    val BOOK_TYPE = 2

    // 六图的随机图
    private val homeSix = intArrayOf(R.drawable.home_six_1, R.drawable.home_six_2, R.drawable.home_six_3, R.drawable.home_six_4, R.drawable.home_six_5, R.drawable.home_six_6, R.drawable.home_six_7, R.drawable.home_six_8, R.drawable.home_six_9, R.drawable.home_six_10, R.drawable.home_six_11, R.drawable.home_six_12, R.drawable.home_six_13, R.drawable.home_six_14, R.drawable.home_six_15, R.drawable.home_six_16, R.drawable.home_six_17, R.drawable.home_six_18, R.drawable.home_six_19, R.drawable.home_six_20, R.drawable.home_six_21, R.drawable.home_six_22, R.drawable.home_six_23)

    // 2张图的随机图
    private val homeTwo = intArrayOf(R.drawable.home_two_one, R.drawable.home_two_two, R.drawable.home_two_three, R.drawable.home_two_four, R.drawable.home_two_five, R.drawable.home_two_six, R.drawable.home_two_eleven, R.drawable.home_two_eight, R.drawable.home_two_nine)

    // 一张图的随机图
    private val homeOne = intArrayOf(R.drawable.home_one_1, R.drawable.home_one_2, R.drawable.home_one_3, R.drawable.home_one_4, R.drawable.home_one_5, R.drawable.home_one_6, R.drawable.home_one_7, R.drawable.home_one_9, R.drawable.home_one_10, R.drawable.home_one_11, R.drawable.home_one_12)

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

    private fun getDefaultPic(imgNumber: Int, position: Int): Int {
        when (imgNumber) {
            1 -> return R.drawable.img_two_bi_one
            2 -> return R.drawable.img_four_bi_three
            3 -> return R.drawable.img_one_bi_one
        }
        return R.drawable.img_four_bi_three
    }

    /**
     * 显示随机的图片(每日推荐)
     *
     * @param imgNumber    有几张图片要显示
     * @param position     第几张图片
     * @param itemPosition 是第几个item，如android是第一个
     * @param imageView    对应图片控件
     */
    fun displayRandom(imgNumber: Int, position: Int, itemPosition: Int, imageView: ImageView) {
        Glide.with(imageView.context)
                .load(getRandomPic(imgNumber, position, itemPosition))
                .placeholder(getDefaultPic(imgNumber, position))
                .error(getDefaultPic(imgNumber, position))
                .crossFade(1500)
                .into(imageView)
    }

    private fun getRandomPic(imgNumber: Int, position: Int, itemPosition: Int): Int {
        val random = Random()
        var randomInt = 0
        when (imgNumber) {
            1 -> {
                randomInt = random.nextInt(homeOne.size)
                return homeOne[randomInt]
            }
            2 -> {
                randomInt = random.nextInt(homeTwo.size)
                return homeTwo[randomInt]
            }
            3 -> {
                randomInt = random.nextInt(homeSix.size)
                return homeSix[randomInt]
            }
        }
        return homeOne[randomInt]
    }
}