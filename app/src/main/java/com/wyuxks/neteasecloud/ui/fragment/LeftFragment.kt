package com.wyuxks.neteasecloud.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.http.rx.RxBus
import com.wyuxks.neteasecloud.http.rx.RxCodeConstants
import com.wyuxks.neteasecloud.ui.adapter.MyFragmentPagerAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.ui.fragment.child.AndroidFragment
import com.wyuxks.neteasecloud.ui.fragment.child.RecommendFragment
import com.wyuxks.neteasecloud.ui.fragment.child.SolidFragment
import com.wyuxks.neteasecloud.ui.fragment.child.WelfareFragment
import kotlinx.android.synthetic.main.fragment_left.*
import rx.Subscription
import rx.functions.Action1

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 最左边的fragment
 */
class LeftFragment : BaseFragment() {

    val titles = ArrayList<String>()
    val fragmentList = ArrayList<Fragment>()
    lateinit var subscription: Subscription

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
        loadData()
        initRxBus()
    }

    override fun loadData() {
        showContentView()
    }

    private fun initFragments() {
        titles.add(getString(R.string.recommend))
        titles.add(getString(R.string.welfare))
        titles.add(getString(R.string.solid))
        titles.add(getString(R.string.android))

        fragmentList.add(RecommendFragment())
        fragmentList.add(WelfareFragment())
        fragmentList.add(SolidFragment())
        fragmentList.add(AndroidFragment())


    }

    /**
     * 每日推荐点击"更多"跳转
     */
    private fun initRxBus() {
        subscription = RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE, String::class.java)
                .subscribe({
                    when (it) {
                        "Android" -> vp_left.currentItem = 3
                        "App" -> vp_left.currentItem = 2
                        "iOS" -> vp_left.currentItem = 2
                        "前端" -> vp_left.currentItem = 2
                        "休息视频" -> vp_left.currentItem = 2
                        "瞎推荐" -> vp_left.currentItem = 2
                        "福利" -> vp_left.currentItem = 1
                        "拓展资源" -> vp_left.currentItem = 2
                        "干货" -> vp_left.currentItem = 2
                        else -> vp_left.currentItem = 1
                    }

                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe()
        }
    }
}