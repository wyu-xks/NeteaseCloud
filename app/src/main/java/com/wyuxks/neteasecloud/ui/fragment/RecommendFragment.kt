package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xrecyclerview.XRecyclerView
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.base.BaseFragment

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 推荐fragment
 */
class RecommendFragment : BaseFragment() {



    override fun setLayout(): Int {
        return  R.layout.fragment_recommend
    }

    override fun initView() {
        val xRecyclerView = rootView?.findViewById(R.id.xrv_recommend) as XRecyclerView

    }
}