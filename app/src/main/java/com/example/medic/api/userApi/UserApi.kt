package com.example.medic.api.userApi

import com.example.medic.api.response.SendCodeResponse
import com.example.medic.api.response.SignInResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface UserApi {
    @Headers("accept: application/json")
    @POST("api/sendCode")
    fun sendCode(@Header("email") email:String) : Call<SendCodeResponse>
    @Headers("accept: application/json")
    @POST("api/signin")
    fun signIn(@Header("email") email: String?, @Header("code") code:String) : Call<SignInResponse>
}