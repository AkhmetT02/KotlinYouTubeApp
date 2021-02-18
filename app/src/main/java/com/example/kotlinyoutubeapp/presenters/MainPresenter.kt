package com.example.kotlinyoutubeapp.presenters

import com.example.kotlinyoutubeapp.models.Item
import com.example.kotlinyoutubeapp.models.Result
import com.example.kotlinyoutubeapp.models.VideoModel
import com.example.kotlinyoutubeapp.providers.MainProvider
import com.example.kotlinyoutubeapp.views.MainView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun startLoadSearchVideos(pageToken: String?, query: String?) {
        viewState.startLoading()
        MainProvider(this).loadSearchVideos("snippet", query = query, pageToken = pageToken)
    }

    fun finishLoadSearchVideos(result: Result?) {
        viewState.endLoading()
        viewState.setupResultVideos(result?.items)
        viewState.setNextPageToken(result?.nextPageToken)
    }


    fun loadError(text: String) {
        viewState.showError(text = text)
    }
}