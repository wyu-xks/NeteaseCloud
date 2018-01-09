package com.wyuxks.neteasecloud.http

import com.wyuxks.neteasecloud.bean.BannerBean
import com.wyuxks.neteasecloud.bean.GankIoDataBean
import com.wyuxks.neteasecloud.bean.GankIoDayBean
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

    /**
     * 轮播图
     */
    @GET("banner/qryBannerList")
    fun getBnanerData(): Observable<BannerBean>


    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2015/08/06
     */
    @GET("day/{year}/{month}/{day}")
    fun getGankIoDay(@Path("year") year: String, @Path("month") month: String, @Path("day") day: String): Observable<GankIoDayBean>

    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("/v2/movie/in_theaters")
    fun getHotMovie(): Observable<HotMovieBean>

}