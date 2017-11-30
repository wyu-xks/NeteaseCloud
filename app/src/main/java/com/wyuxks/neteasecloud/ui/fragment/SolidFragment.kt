package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_solid.*

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 干货fragment
 */
class SolidFragment : BaseFragment() {

    override fun setLayout(): Int = R.layout.fragment_solid


    override fun initView() {
    }

    override fun loadData() {
        Handler().postDelayed({
            showContentView()
        }, 1000)
    }
}