package com.example.medic.data

import android.content.SharedPreferences

object User {
    lateinit var sharedPrefs: SharedPreferences
    var email:String? =""
    var password:String?=""
    var token:String?=""
    var isCardCreated:Boolean? = false
}