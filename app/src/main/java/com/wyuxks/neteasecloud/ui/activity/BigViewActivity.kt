package com.wyuxks.neteasecloud.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.adapter.PhotoViewAdapter
import com.wyuxks.neteasecloud.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_big_photo.*
import uk.co.senab.photoview.PhotoViewAttacher

/**
 *  Author : xks
 *  Data : 2017/12/5 0005
 *  Des :
 */
class BigViewActivity : FragmentActivity(), View.OnClickListener, PhotoViewAttacher.OnPhotoTapListener, ViewPager.OnPageChangeListener {



    var index: Int = 0
    lateinit var imageuri: ArrayList<String>
    lateinit var photoViewAdapter: PhotoViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_photo)
        val bundle = intent.extras
        index = bundle.getInt("position")
        imageuri = bundle.getStringArrayList("imageuri")
        photoViewAdapter = PhotoViewAdapter(this, imageuri, this)
        photo_pager.adapter = photoViewAdapter
        photo_pager.currentItem = index
        num_of_pic.text = ("${index + 1}" + " / " + "${imageuri.size}")
        photo_pager.addOnPageChangeListener(this)
    }

    //单击大图回调
    override fun onPhotoTap(view: View?, x: Float, y: Float) {
        finish()
    }

    override fun onOutsidePhotoTap() {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        num_of_pic.text = ("${position + 1}" + " / " + "${imageuri.size}")
        index = position
    }

    override fun onClick(v: View?) {

    }
}