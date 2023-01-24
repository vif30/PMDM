package com.viizfo.youtubewithoutapi

import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

class VideoAdapter(var youtubeVideoList: MutableList<YouTubeVideos>?):RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_view, parent, false)

        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoWeb.loadData(youtubeVideoList?.get(position)?.videoUrl?:"", "text/html" , "utf-8" )
    }

    override fun getItemCount() = youtubeVideoList?.size ?: 0


    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoWeb: WebView

        init {
            videoWeb = itemView.findViewById(R.id.wvVideo) as WebView
            videoWeb.getSettings().setJavaScriptEnabled(true)
            videoWeb.setWebChromeClient(object : WebChromeClient() {})
        }
    }

}