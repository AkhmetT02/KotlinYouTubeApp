package com.example.kotlinyoutubeapp.retrofit

import com.example.kotlinyoutubeapp.helpers.YouTubeHelper
import com.example.kotlinyoutubeapp.models.Result
import com.example.kotlinyoutubeapp.models.VideoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET(YouTubeHelper.KEY_SEARCH)
    fun getSearchVideoList(@Query(YouTubeHelper.PARAM_KEY) api_key: String,
                           @Query(YouTubeHelper.PARAM_PART) part: String,
                           @Query(YouTubeHelper.PARAM_QUERY) query: String?,
                           @Query(YouTubeHelper.PARAM_PAGE_TOKEN) pageToken: String?): Call<Result>
}