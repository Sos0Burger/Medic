package com.example.medic.api.newsApi

import com.example.medic.api.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {

    @Headers("accept: application/json")
    @GET("api/news")
    fun news(): Call<List<NewsResponse>>
}