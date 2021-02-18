package com.example.kotlinyoutubeapp.providers

import android.util.Log
import com.example.kotlinyoutubeapp.helpers.YouTubeHelper
import com.example.kotlinyoutubeapp.models.Result
import com.example.kotlinyoutubeapp.models.VideoModel
import com.example.kotlinyoutubeapp.presenters.MainPresenter
import com.example.kotlinyoutubeapp.retrofit.RetrofitCommon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainProvider(private val presenter: MainPresenter) {


    fun loadSearchVideos(part: String, query: String?, pageToken: String?) = CoroutineScope(Dispatchers.IO).launch {
        RetrofitCommon.getRetrofitService.getSearchVideoList(api_key = YouTubeHelper.API_KEY, part = part, query = query, pageToken = pageToken).enqueue(object: Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                presenter.loadError(t.message!!)
                Log.i("ResultThrowable", t.message!!)
            }
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val videoList: ArrayList<VideoModel> = ArrayList()
                presenter.finishLoadSearchVideos(response.body())
            }
        })
    }
}