package com.wyuxks.neteasecloud.ui.fragment.child

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.adapter.SolidAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.utils.findViewOften
import kotlinx.android.synthetic.main.fragment_solid.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 干货fragment
 */
class SolidFragment : BaseFragment() {

    var type = "all"
    var page = 1

    override fun setLayout(): Int = R.layout.fragment_solid
    var solidAdapter = SolidAdapter(true)

    override fun initView() {

        val header = LayoutInflater.from(context).inflate(R.layout.header_item_gank_custom, null)
        xrv_solid.addHeaderView(header)
        initHeader(header)
        xrv_solid.layoutManager = LinearLayoutManager(context)
        // 需加，不然滑动不流畅
        xrv_solid.isNestedScrollingEnabled = false
        xrv_solid.setHasFixedSize(false)
        xrv_solid.itemAnimator = DefaultItemAnimator()
        xrv_solid.adapter = solidAdapter
        xrv_solid.setPullRefreshEnabled(true)
        loadData()
        initEvent()
    }

    private fun initEvent() {
        xrv_solid.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                loadData()
            }

            override fun onLoadMore() {
                loadMore()
            }
        })
    }


    private fun initHeader(header: View) {
//        val title = header.findViewOften<TextView>(R.id.tx_name)
        val select = header.findViewOften<LinearLayout>(R.id.ll_choose_catalogue)
        select.setOnClickListener { toast(context, "点击了选择") }

    }

    override fun loadData() {
        page = 1
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoData(type, page++, HttpManager.per_page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDataBean> {
                    override fun onNext(t: GankIoDataBean) {
                        loadDataSuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }

                    override fun onCompleted() {
                        xrv_solid.refreshComplete()
                    }

                })


    }

    private fun loadMore() {
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoData(type, page++, HttpManager.per_page)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDataBean> {
                    override fun onNext(t: GankIoDataBean) {
                        if (t?.results?.size ?: -1 > 0) {
                            solidAdapter.addAll(t.results)
                        } else {
                            xrv_solid.noMoreLoading()
                        }

                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }

                    override fun onCompleted() {
                        xrv_solid.refreshComplete()
                    }

                })
    }


    fun loadDataSuccess(t: GankIoDataBean) {
        showContentView()
        solidAdapter.clear()
        solidAdapter.addAll(t.results)
    }
}