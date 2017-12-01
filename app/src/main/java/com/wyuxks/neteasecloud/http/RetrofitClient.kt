package com.wyuxks.neteasecloud.http

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
    @GET("top250")
    fun getTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<HotMovieBean>

    //获取豆瓣Top250 榜单
    @FormUrlEncoded
    @POST("top250")
    fun getTop250Movies(@Field("start") start: Int, @Field("count") count: Int): Observable<HotMovieBean>

}