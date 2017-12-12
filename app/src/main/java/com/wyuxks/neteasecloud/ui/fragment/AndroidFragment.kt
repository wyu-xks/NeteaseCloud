package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.adapter.viewholder.SolidAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.utils.findViewOften
import kotlinx.android.synthetic.main.fragment_android.*
import kotlinx.android.synthetic.main.fragment_solid.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 安卓fragment
 */
class AndroidFragment : BaseFragment() {




    var type = "Android"
    var page = 1
    override fun setLayout(): Int = R.layout.fragment_android
    var solidAdapter = SolidAdapter(false)

    override fun initView() {
        xrv_android.layoutManager = LinearLayoutManager(context)
        // 需加，不然滑动不流畅
        xrv_android.isNestedScrollingEnabled = false
        xrv_android.setHasFixedSize(false)
        xrv_android.itemAnimator = DefaultItemAnimator()
        xrv_android.adapter = solidAdapter
        xrv_android.setPullRefreshEnabled(true)
        loadData()
        initEvent()
    }

    private fun initEvent() {
        xrv_android.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                loadData()
            }

            override fun onLoadMore() {
                loadMore()
            }
        })
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
                        xrv_android.refreshComplete()
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
                            xrv_android.noMoreLoading()
                        }

                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }

                    override fun onCompleted() {
                        xrv_android.refreshComplete()
                    }

                })
    }


    fun loadDataSuccess(t: GankIoDataBean) {
        showContentView()
        solidAdapter.clear()
        solidAdapter.addAll(t.results)
    }

}