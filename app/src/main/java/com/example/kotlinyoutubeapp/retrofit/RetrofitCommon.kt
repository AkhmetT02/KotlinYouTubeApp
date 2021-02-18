package com.example.kotlinyoutubeapp.retrofit

import com.example.kotlinyoutubeapp.helpers.YouTubeHelper
import retrofit2.create

object RetrofitCommon {

    val getRetrofitService: RetrofitService
        get() = RetrofitInstance.getRetrofitInstance(YouTubeHelper.BASE_URL).create()
}