package com.wyuxks.neteasecloud.ui.fragment.child

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.xrecyclerview.XRecyclerView
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.activity.BigViewActivity
import com.wyuxks.neteasecloud.ui.adapter.WelfareAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.ui.base.baseadpter.OnItemClickListener
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

    lateinit var welfareAdapter: WelfareAdapter
    var page = 1

    override fun setLayout(): Int = R.layout.fragment_welfare

    override fun initView() {
//        xrv_welfare = rootView.findViewById(R.id.xrv_welfare) as XRecyclerView
        xrv_welfare.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        welfareAdapter = WelfareAdapter(object : OnItemClickListener<String> {
            override fun onClick(t: String, position: Int) {
                val bundle = Bundle()
                bundle.putInt("position", position)//第几张
                bundle.putStringArrayList("imageuri", welfareAdapter.data)
                val intent = Intent(context, BigViewActivity::class.java)
                intent.putExtras(bundle)
                context?.startActivity(intent)
            }
        })
        xrv_welfare.adapter = welfareAdapter
        xrv_welfare.setPullRefreshEnabled(true)
        xrv_welfare.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                loadData()
            }

            override fun onLoadMore() {
                loadMore()
            }
        })
        loadData()

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
                            loadDataSuccess()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        showError()

                    }

                    override fun onCompleted() {
                        xrv_welfare.refreshComplete()
                    }

                })


    }

    fun loadMore() {
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoData(getString(R.string.welfare), page++, HttpManager.per_page_more)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDataBean> {
                    override fun onNext(t: GankIoDataBean?) {
                        xrv_welfare.refreshComplete()
                        if (t?.results?.size ?: -1 > 0) {
                            t?.results?.forEach {
                                welfareAdapter.add(it.url)
                            }
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