package com.wyuxks.neteasecloud.ui.fragment

import android.os.Handler
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.xrecyclerview.XRecyclerView
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.ui.adapter.WelfareAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welfare.*

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 福利fragment
 */
class WelfareFragment : BaseFragment() {

//    lateinit var xrv_welfare: XRecyclerView

    var lists = ArrayList<String>()

    override fun setLayout(): Int = R.layout.fragment_welfare

    override fun initView() {
//        xrv_welfare = rootView.findViewById(R.id.xrv_welfare) as XRecyclerView
        val welfareAdapter = WelfareAdapter()
        initList()
        welfareAdapter.addAll(lists)
        xrv_welfare.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        xrv_welfare.adapter = welfareAdapter
        xrv_welfare.setPullRefreshEnabled(true)
        xrv_welfare.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                Handler().postDelayed({
                    xrv_welfare.refreshComplete()
                }, 2000)
            }

            override fun onLoadMore() {
                Handler().postDelayed({
                    xrv_welfare.refreshComplete()
                }, 2000)
            }
        })

    }

    private fun initList() {
        lists.add("http://218.17.157.212:9999/fog/test/image/jpeg/3f96bc0356bc08899f2ffd6ca859ffe4.jpg")
        lists.add("http://218.17.157.212:9999/fog/test/image/jpeg/b9f49a5c3590722740c573ea73407a64.jpg")
        lists.add("http://218.17.157.212:9999/fog/test/image/jpeg/a9954e5b22085701fb29b302b6f73482.jpg")
        lists.add("http://218.17.157.212:9999//fog/test/image/jpeg/c390051383c5131f8b212d18d84b6112.jpeg")
        lists.add("http://218.17.157.212:9999//fog/test/image/jpeg/ef675e3b4c02fc3795d0d1fb395d73c3.jpeg")
        lists.add("http://218.17.157.212:9999//fog/test/image/jpeg/ec2d29ef517bd7d819cca62d2e15a23b.jpeg")
    }
}