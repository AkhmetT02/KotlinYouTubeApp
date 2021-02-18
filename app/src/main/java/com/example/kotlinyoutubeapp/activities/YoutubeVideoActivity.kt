package com.example.kotlinyoutubeapp.activities

import android.os.Bundle
import com.example.kotlinyoutubeapp.R
import com.example.kotlinyoutubeapp.helpers.YouTubeHelper
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class YoutubeVideoActivity : YouTubeBaseActivity() {

    private lateinit var playerListener: YouTubePlayer.OnInitializedListener
    private lateinit var player: YouTubePlayerView

    private var currentTimeForSave: Int? = 0
    private var currentTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)

        if (savedInstanceState != null) {
             currentTime = savedInstanceState.getInt("currentTimeInMillis")
        }

        player = findViewById(R.id.youtube_player)

        val videoKey = intent.getStringExtra("videoKey")

        player.initialize(YouTubeHelper.API_KEY, getYoutubePlayerListener(videoKey))
    }

    private fun getYoutubePlayerListener(videoUri: String?): YouTubePlayer.OnInitializedListener {
        playerListener = object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, p2: Boolean) {
                player?.loadVideo(videoUri)
                if (currentTime != 0) {
                    player?.seekToMillis(currentTime)
                }
                currentTimeForSave = player?.currentTimeMillis
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

            }
        }

        return playerListener
    }

    override fun onSaveInstanceState(p0: Bundle) {
        p0.putInt("currentTimeInMillis", currentTimeForSave!!)
        super.onSaveInstanceState(p0)
    }
}