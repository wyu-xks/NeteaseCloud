package com.wyuxks.neteasecloud.ui.fragment

import android.content.Intent
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.example.xrecyclerview.XRecyclerView
import com.huayuni.kotlinlearn.utils.e
import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.NeteaseCloud
import com.wyuxks.neteasecloud.R
import com.wyuxks.neteasecloud.bean.BannerBean
import com.wyuxks.neteasecloud.bean.EveryDayItemBean
import com.wyuxks.neteasecloud.bean.GankIoDayBean
import com.wyuxks.neteasecloud.bean.ItemBean
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import com.wyuxks.neteasecloud.http.HttpManager
import com.wyuxks.neteasecloud.http.RetrofitClient
import com.wyuxks.neteasecloud.http.rx.RxBus
import com.wyuxks.neteasecloud.http.rx.RxBusBaseMessage
import com.wyuxks.neteasecloud.http.rx.RxCodeConstants
import com.wyuxks.neteasecloud.ui.activity.WebActivity
import com.wyuxks.neteasecloud.ui.adapter.RecommendAdapter
import com.wyuxks.neteasecloud.ui.base.BaseFragment
import com.wyuxks.neteasecloud.ui.base.baseadpter.OnItemClickListener
import com.wyuxks.neteasecloud.ui.image.GlideImageLoader
import com.wyuxks.neteasecloud.utils.Utils
import com.wyuxks.neteasecloud.utils.findViewOften
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerClickListener
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
class RecommendFragment : BaseFragment(), View.OnClickListener, OnBannerClickListener {


    var recommendAdapter = RecommendAdapter()
    var banner: Banner? = null
    lateinit var ib_xiandu: ImageView
    lateinit var ib_movie_hot: ImageView
    lateinit var daily_btn: ImageView
    lateinit var tv_daily_text: TextView
    var bannerImages: MutableList<String> = ArrayList()
    lateinit var todayTimes: ArrayList<String>

    override fun setLayout(): Int = R.layout.fragment_recommend
    override fun initView() {
        initBanner()
        val header = LayoutInflater.from(context).inflate(R.layout.header_item_recommend, null)
        banner = header.findViewOften<Banner>(R.id.banner)
        ib_xiandu = header.findViewOften<ImageView>(R.id.ib_xiandu)
        ib_movie_hot = header.findViewOften<ImageView>(R.id.ib_movie_hot)
        daily_btn = header.findViewOften<ImageView>(R.id.daily_btn)
        tv_daily_text = header.findViewOften<TextView>(R.id.tv_daily_text)
        xrv_recommend.addHeaderView(header)
        xrv_recommend.layoutManager = LinearLayoutManager(context)
        // 需加，不然滑动不流畅
        xrv_recommend.isNestedScrollingEnabled = false
        xrv_recommend.setHasFixedSize(false)
        xrv_recommend.itemAnimator = DefaultItemAnimator()
        xrv_recommend.adapter = recommendAdapter
        xrv_recommend.setPullRefreshEnabled(true)
        todayTimes = Utils.getTodayTime()
        tv_daily_text.text = todayTimes.get(2)
        initEvent()
        loadData()
    }

    private fun initBanner() {
        HttpManager.instance.getNTRetrofit()?.create(RetrofitClient::class.java)?.getBnanerData()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BannerBean> {
                    override fun onNext(t: BannerBean?) {
                        if (t?.retCode == 0) {
                            bannerImages.clear()
                            t?.data?.forEach {
                                bannerImages.add(it.bannerurl)
                            }
                            startBanner()
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }
                })

    }

    private fun startBanner() {
        banner?.isAutoPlay(true)
                ?.setImageLoader(GlideImageLoader())
                ?.setImages(bannerImages)
                ?.setDelayTime(4000)
                ?.start()
                ?.setOnBannerClickListener(this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            banner?.start()
        } else {
            banner?.stopAutoPlay()
        }
    }

    override fun OnBannerClick(position: Int) {

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
                    xrv_recommend.noMoreLoading()
                }, 2000)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_xiandu -> startWebActivity(" https://gank.io/xiandu", "干货闲读")
            R.id.daily_btn -> {}
            R.id.ib_movie_hot -> RxBus.getDefault().post(RxCodeConstants.JUMP_TYPE_TO_ONE, "电影")
        }
    }

    fun startWebActivity(url: String, title: String) {
        val instance = NeteaseCloud.instance
        val intent = Intent(instance, WebActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title", title)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        instance.startActivity(intent)
    }

    override fun loadData() {
        HttpManager.instance.getGIRetrofit()?.create(RetrofitClient::class.java)?.getGankIoDay(todayTimes.get(0), todayTimes.get(1), todayTimes.get(2))
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<GankIoDayBean> {
                    override fun onNext(t: GankIoDayBean?) {
                        loadDataSuccess(t)
                    }

                    override fun onError(e: Throwable?) {
                        showError()
                    }

                    override fun onCompleted() {

                    }

                })

    }

    fun loadDataSuccess(t: GankIoDayBean?) {
        recommendAdapter.setData(t)
        e("result：" + t.toString())
        showContentView()
        xrv_recommend.visibility = View.VISIBLE
        ll_loading.visibility = View.INVISIBLE
        xrv_recommend.refreshComplete()
    }

}