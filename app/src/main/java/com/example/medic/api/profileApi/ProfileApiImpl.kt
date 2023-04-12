package com.example.medic.api.profileApi

import com.example.medic.api.RetrofitClient
import com.example.medic.api.response.CreateProfileResponse
import com.example.medic.data.Profile
import retrofit2.Call

private val profileApi = RetrofitClient.getInstance().create(ProfileApi::class.java)

class ProfileApiImpl: ProfileApi{
    override fun createProfile(profile: Profile, token:String): Call<CreateProfileResponse> {
        return profileApi.createProfile(profile, token)
    }
}