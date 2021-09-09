package com.example.everydaynewa.network

import com.example.everydaynewa.model.NewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("v2/top-headlines/")
    fun getNewsToday(@Query("country")country:String,@Query("page")page:Int,@Query("q")searchKey:String):Single<NewsModel>
}