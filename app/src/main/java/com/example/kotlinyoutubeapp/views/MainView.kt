package com.example.kotlinyoutubeapp.views

import com.example.kotlinyoutubeapp.models.Item
import com.example.kotlinyoutubeapp.models.VideoModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setupResultVideos(items: List<Item>?)
    fun setNextPageToken(token: String?)
    fun showError(text: String)
    fun startLoading()
    fun endLoading()
}