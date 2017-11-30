package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.BaseFragment

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 安卓fragment
 */
class AndroidFragment : BaseFragment() {

    override fun setLayout(): Int = R.layout.fragment_android
    override fun initView() {
    }

    override fun loadData() {
        Handler().postDelayed({
            showError()
        }, 1000)
    }

}