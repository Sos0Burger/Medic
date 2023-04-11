package com.example.medic.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl("https://medic.madskill.ru/").addConverterFactory(MoshiConverterFactory.create()).build()
    }
}