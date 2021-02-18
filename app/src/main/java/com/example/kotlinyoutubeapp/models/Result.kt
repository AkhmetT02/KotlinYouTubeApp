package com.example.kotlinyoutubeapp.models


data class Result(
    val items: List<Item>,
    val nextPageToken: String,
    val prevPageToken: String
)