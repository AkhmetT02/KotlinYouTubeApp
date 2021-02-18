package com.example.kotlinyoutubeapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinyoutubeapp.R
import com.example.kotlinyoutubeapp.activities.YoutubeVideoActivity
import com.example.kotlinyoutubeapp.models.Item
import com.squareup.picasso.Picasso

class VideoAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: ArrayList<Item> = ArrayList()
    private var onReachEndListener: OnReachEndListener? = null

    fun setupItems(items: ArrayList<Item>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }
    fun clearItems() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoViewHolder) {
            holder.bind(items[position])
        }

        if (items.size % 5 == 0 && position > items.size - 3 && onReachEndListener != null) {
            onReachEndListener?.onReachEnd()
        }
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val videoImg: ImageView = itemView.findViewById(R.id.video_thumb_img_item)
        private val videoTitleTv: TextView = itemView.findViewById(R.id.video_title_tv_item)
        private val videoChannelTv: TextView = itemView.findViewById(R.id.video_channel_tv_item)

        fun bind(item: Item) {
            val videoModel = item.snippet
            Picasso.get().load(videoModel.thumbnails.high.url).into(videoImg)
            videoTitleTv.text = videoModel.title
            videoChannelTv.text = videoModel.channelTitle

            itemView.setOnClickListener {
                val intentYouTubeVideo = Intent(context, YoutubeVideoActivity::class.java)
                intentYouTubeVideo.putExtra("videoKey", item.id.videoId)
                context.startActivity(intentYouTubeVideo)
            }
        }
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }
}