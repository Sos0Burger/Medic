package com.example.medic.api.userApi

import com.example.medic.api.response.SendCodeResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {
    @Headers("accept: application/json")
    @POST("api/sendCode")
    fun sendCode(@Header("email") email:String) : Call<SendCodeResponse>
}