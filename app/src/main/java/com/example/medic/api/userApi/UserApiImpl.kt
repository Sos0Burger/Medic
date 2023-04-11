package com.example.medic.api.userApi

import com.example.medic.api.RetrofitClient
import com.example.medic.api.response.SendCodeResponse
import retrofit2.Call

private val userApi = RetrofitClient.getInstance().create(UserApi::class.java)

class UserApiImpl: UserApi {
    override fun sendCode(email: String) : Call<SendCodeResponse> {
        return userApi.sendCode(email)
    }
}