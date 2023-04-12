package com.example.medic.api.newsApi

import com.example.medic.api.RetrofitClient
import com.example.medic.api.profileApi.ProfileApi
import com.example.medic.api.response.NewsResponse
import retrofit2.Call

private val newsApi = RetrofitClient.getInstance().create(NewsApi::class.java)
class NewsApiIml:NewsApi {
    override fun news(): Call<List<NewsResponse>> {
        return newsApi.news()
    }
}