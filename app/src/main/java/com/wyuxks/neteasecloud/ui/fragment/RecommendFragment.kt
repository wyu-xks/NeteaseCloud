package com.wyuxks.neteasecloud.ui.fragment

import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.e
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.ui.adapter.RecommendAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.utils.findViewOften
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fragment_recommend.*
import retrofit2.Retrofit
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Author : xks
 *  Data : 2017/11/21 0021
 *  Des : 首页 推荐fragment
 */
class RecommendFragment : BaseFragment(), View.OnClickListener {

    var recommendAdapter = RecommendAdapter()
    var lists = ArrayList<Int>()
    lateinit var banner: Banner
    lateinit var ib_xiandu: ImageView
    lateinit var ib_movie_hot: ImageView
    lateinit var daily_btn: ImageView
    lateinit var retrofit: Retrofit

    override fun setLayout(): Int = R.layout.fragment_recommend
    override fun initView() {
        initList()
        val header = LayoutInflater.from(context).inflate(R.layout.header_item_recommend, null)
        banner = header.findViewOften<Banner>(R.id.banner)
        ib_xiandu = header.findViewOften<ImageView>(R.id.ib_xiandu)
        ib_movie_hot = header.findViewOften<ImageView>(R.id.ib_movie_hot)
        daily_btn = header.findViewOften<ImageView>(R.id.daily_btn)
        xrv_recommend.addHeaderView(header)
        xrv_recommend.layoutManager = LinearLayoutManager(context)
        // 需加，不然滑动不流畅
        xrv_recommend.isNestedScrollingEnabled = false
        xrv_recommend.setHasFixedSize(false)
        xrv_recommend.itemAnimator = DefaultItemAnimator()
        xrv_recommend.adapter = recommendAdapter
        xrv_recommend.setPullRefreshEnabled(true)
        initEvent()
    }

    private fun initEvent() {
        ib_xiandu.setOnClickListener(this)
        ib_movie_hot.setOnClickListener(this)
        daily_btn.setOnClickListener(this)
        xrv_recommend.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                loadData()
            }

            override fun onLoadMore() {
                Handler().postDelayed({
                    xrv_recommend.refreshComplete()
                }, 2000)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_xiandu -> toast(context.applicationContext, "干货闲读")
            R.id.daily_btn -> toast(context.applicationContext, "每日推荐")
            R.id.ib_movie_hot -> toast(context.applicationContext, "电影热映")
        }
    }

    private fun initList() {
        lists.add(1)
        lists.add(2)
        lists.add(1)
        lists.add(3)
        lists.add(1)
        lists.add(2)
        lists.add(1)
        lists.add(4)
        lists.add(1)
        lists.add(3)
    }

    override fun loadData() {
        HttpManager.instance.getDBRetrofit()?.create(RetrofitClient::class.java)?.getTop250(0, 20)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<HotMovieBean> {
                    override fun onNext(t: HotMovieBean?) {
                        loadDataSuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }
                    override fun onCompleted() {

                    }

                })

    }

    fun loadDataSuccess(t: HotMovieBean?) {
        toast(context, "请求成功")
        val hotMovieBean = t
        e("result：" + hotMovieBean.toString())
        showContentView()
        recommendAdapter.addAll(lists)
        recommendAdapter.notifyDataSetChanged()
        xrv_recommend.visibility = View.VISIBLE
        ll_loading.visibility = View.INVISIBLE
        xrv_recommend.refreshComplete()
    }
}