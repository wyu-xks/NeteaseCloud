package com.wyuxks.neteasecloud.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.BannerBean
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.adapter.MoviesAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.utils.findViewOften
import kotlinx.android.synthetic.main.fragment_recommend.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 中间的fragment
 */
class MiddleFragment : BaseFragment(), View.OnClickListener {


    lateinit var xRecyclerView: XRecyclerView
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var topMoviesRl: RelativeLayout
    lateinit var header: View

    override fun setLayout(): Int {
        return R.layout.fragment_middle
    }


    override fun initView() {
        xRecyclerView = childView.findViewOften<XRecyclerView>(R.id.list_movies)
        header = View.inflate(context, R.layout.middle_header_layout, null)
        topMoviesRl = header.findViewOften<RelativeLayout>(R.id.top_movies_rl)
        xRecyclerView.addHeaderView(header)
        moviesAdapter = MoviesAdapter()
        xRecyclerView.layoutManager = LinearLayoutManager(context)
        // 需加，不然滑动不流畅
        xRecyclerView.isNestedScrollingEnabled = false
        xRecyclerView.setHasFixedSize(false)
        xRecyclerView.itemAnimator = DefaultItemAnimator()
        xRecyclerView.adapter = moviesAdapter
        xRecyclerView.setPullRefreshEnabled(true)
        topMoviesRl.setOnClickListener(this)
        xRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                loadData()
            }

            override fun onLoadMore() {
                xRecyclerView.noMoreLoading()
            }

        })
        loadData()
    }

    override fun loadData() {
        HttpManager.instance.getDBRetrofit()?.create(RetrofitClient::class.java)?.getHotMovie()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<HotMovieBean> {
                    override fun onNext(t: HotMovieBean) {
                        showContentView()
                        t.let {
                            moviesAdapter.clear()
                            moviesAdapter.addAll(it.subjects)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }

                    override fun onCompleted() {
                        xRecyclerView.refreshComplete()
                    }

                })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.top_movies_rl -> toast(context, "top rl")
        }

    }

}