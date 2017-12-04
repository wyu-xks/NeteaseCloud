package com.wyuxks.neteasecloud.http

import com.huayuni.kotlinlearn.utils.toast
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 *  Author : xks
 *  Data : 2017/12/1 0001
 *  Des :
 */
class HttpManager {

    val API_DOUBAN = "https://api.douban.com/"
    val API_GANKIO = "http://gank.io/api/"
    val API_DONGTING = "http://api.dongting.com/"

    val DEFAULT_TIME_OUT = 5L  //超时时间 5s
    val DEFAULT_READ_TIME_OUT = 10L //读操作超时时间 10s
    var douBanRetrofit: Retrofit? = null
    var gankIoRetrofit: Retrofit? = null
    var dongTingRetrofit: Retrofit? = null
    var builder: OkHttpClient.Builder


    init {
        builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)  //连接超时时间
        // builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS) //写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)    //读操作超时时间
        // 添加公共参数拦截器
        val commonInterceptor = HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform", "android")
                .addHeaderParams("userToken", "1234343434dfdfd3434")
                .addHeaderParams("userId", "123445")
                .build()
        builder.addInterceptor(commonInterceptor)
    }

    private object Holder {
        val INSTANCE = HttpManager()
    }

    companion object {
        val instance: HttpManager by lazy { Holder.INSTANCE }
        //分页数据，每页的数量
        val per_page = 10
        val per_page_more = 20
    }

    fun getDBRetrofit(): Retrofit? = douBanRetrofit ?: Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_DOUBAN)
            .build()

    fun getGIRetrofit(): Retrofit? = gankIoRetrofit ?: Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_GANKIO)
            .build()

    fun getDTRetrofit(): Retrofit? = dongTingRetrofit ?: Retrofit.Builder()
            .client(builder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_DONGTING)
            .build()

}