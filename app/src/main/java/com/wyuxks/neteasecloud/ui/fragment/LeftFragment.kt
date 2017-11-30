package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.adapter.MyFragmentPagerAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_left.*

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 最左边的fragment
 */
class LeftFragment : BaseFragment() {

    val titles = ArrayList<String>()
    val fragmentList = ArrayList<Fragment>()

    override fun setLayout(): Int {
        return R.layout.fragment_left
    }

    override fun initView() {
        initFragments()
        val myFragmentPagerAdapter = MyFragmentPagerAdapter(childFragmentManager, fragmentList, titles)
        val tab_left = rootView?.findViewById(R.id.tab_left) as TabLayout
        val vp_left = rootView?.findViewById(R.id.vp_left) as ViewPager
        vp_left.adapter = myFragmentPagerAdapter
        vp_left.currentItem = 0
        vp_left.offscreenPageLimit = 3
        tab_left.tabMode = TabLayout.MODE_FIXED
        tab_left.setupWithViewPager(vp_left)
        myFragmentPagerAdapter.notifyDataSetChanged()

    }

    override fun loadData() {
        Handler().postDelayed({
            showContentView()
        }, 1000)
    }

    private fun initFragments() {
        titles.add("推荐")
        titles.add("福利")
        titles.add("干货")
        titles.add("安卓")

        fragmentList.add(RecommendFragment())
        fragmentList.add(WelfareFragment())
        fragmentList.add(SolidFragment())
        fragmentList.add(AndroidFragment())


    }
}