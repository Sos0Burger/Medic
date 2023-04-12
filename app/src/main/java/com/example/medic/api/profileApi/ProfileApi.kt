package com.example.medic.api.profileApi

import com.example.medic.api.response.CreateProfileResponse
import com.example.medic.data.Profile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface ProfileApi {
    @POST("api/createProfile")
    @Headers("accept: application/json", "Content-Type: application/json")
    fun createProfile(@Body profile: Profile, @Header("Authorization") token:String): Call<CreateProfileResponse>
}