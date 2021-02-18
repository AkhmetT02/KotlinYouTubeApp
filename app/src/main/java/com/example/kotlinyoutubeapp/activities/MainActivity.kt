package com.example.kotlinyoutubeapp.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.view.menu.BaseMenuPresenter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinyoutubeapp.R
import com.example.kotlinyoutubeapp.adapters.VideoAdapter
import com.example.kotlinyoutubeapp.models.Item
import com.example.kotlinyoutubeapp.models.VideoModel
import com.example.kotlinyoutubeapp.presenters.MainPresenter
import com.example.kotlinyoutubeapp.views.MainView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val adapter = VideoAdapter(this)
    private lateinit var recyclerVideos: RecyclerView
    private lateinit var loadingBar: ProgressBar
    private lateinit var queryTxt: EditText

    private var nextPageToken: String? = null
    private var lastQueryText: String? = null

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerVideos = findViewById(R.id.recycler_videos)
        loadingBar = findViewById(R.id.loading_bar_main)
        queryTxt = findViewById(R.id.query_et)

        recyclerVideos.adapter = adapter
        recyclerVideos.layoutManager = LinearLayoutManager(this)

        presenter.startLoadSearchVideos(pageToken = null, query = null)

        adapter.setOnReachEndListener(object: VideoAdapter.OnReachEndListener {
            override fun onReachEnd() {
                presenter.startLoadSearchVideos(pageToken = nextPageToken, query = lastQueryText)
            }
        })
        queryTxt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.clearItems()
                presenter.startLoadSearchVideos(pageToken = null, query = s.toString())
                lastQueryText = s.toString()
            }
        })
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun setupResultVideos(items: List<Item>?) {
        adapter.setupItems(items as ArrayList)
    }

    override fun setNextPageToken(token: String?) {
        nextPageToken = token
    }

    override fun startLoading() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun endLoading() {
        loadingBar.visibility = View.INVISIBLE
    }
}