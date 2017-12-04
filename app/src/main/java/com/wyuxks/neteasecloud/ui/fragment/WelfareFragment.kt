package com.wyuxks.neteasecloud.ui.fragment

import android.os.Handler
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.e
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.adapter.WelfareAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.android.synthetic.main.fragment_welfare.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 福利fragment
 */
class WelfareFragment : BaseFragment() {

//    lateinit var xrv_welfare: XRecyclerView

    var lists = ArrayList<String>()
    var welfareAdapter = WelfareAdapter()
    var page = 1

    override fun setLayout(): Int = R.layout.fragment_welfare

    override fun initView() {
//        xrv_welfare = rootView.findViewById(R.id.xrv_welfare) as XRecyclerView
        xrv_welfare.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        xrv_welfare.adapter = welfareAdapter
        xrv_welfare.setPullRefreshEnabled(true)
        xrv_welfare.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                Handler().postDelayed({
                    loadData()
                }, 2000)
            }

            override fun onLoadMore() {
                loadMore()

            }
        })
    }


    override fun loadData() {
        page = 1
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoData(getString(R.string.welfare), page++, HttpManager.per_page_more)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDataBean> {
                    override fun onNext(t: GankIoDataBean?) {
                        xrv_welfare.refreshComplete()
                        if (t?.results?.size ?: -1 > 0) {
                            welfareAdapter.clear()
                            t?.results?.forEach {
                                welfareAdapter.add(it.url)
                            }
                            welfareAdapter.notifyDataSetChanged()
                            loadDataSuccess()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        xrv_welfare.refreshComplete()
                        showError()

                    }

                    override fun onCompleted() {
                        xrv_welfare.refreshComplete()
                    }

                })


    }

    fun loadMore() {
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoData(getString(R.string.welfare), page ++, HttpManager.per_page_more)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDataBean> {
                    override fun onNext(t: GankIoDataBean?) {
                        xrv_welfare.refreshComplete()
                        if (t?.results?.size ?: -1 > 0) {
                            t?.results?.forEach {
                                welfareAdapter.add(it.url)
                            }
                            welfareAdapter.notifyDataSetChanged()
                        } else {
                            xrv_welfare.noMoreLoading()
                        }


                    }

                    override fun onError(e: Throwable?) {
                        xrv_welfare.refreshComplete()

                    }

                    override fun onCompleted() {
                        xrv_welfare.refreshComplete()
                    }

                })
    }


    fun loadDataSuccess() {
        showContentView()
        xrv_welfare.visibility = View.VISIBLE
    }

}