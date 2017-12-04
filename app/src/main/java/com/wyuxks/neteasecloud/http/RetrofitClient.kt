package com.wyuxks.neteasecloud.http

import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.bean.movies.HotMovieBean
import retrofit2.Call
import retrofit2.http.*
import rx.Observable


/**
 *  Author : xks
 *  Data : 2017/12/1 0001
 *  Des :
 */
interface RetrofitClient{

    //获取豆瓣Top250 榜单
    @GET("v2/movie/top250")
    fun getTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<HotMovieBean>

    //获取豆瓣Top250 榜单
    @FormUrlEncoded
    @POST("v2/movie/top250")
    fun getTop250Movies(@Field("start") start: Int, @Field("count") count: Int): Observable<HotMovieBean>

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    fun getGankIoData(@Path("type") id: String, @Path("page") page: Int, @Path("pre_page") pre_page: Int): Observable<GankIoDataBean>

}