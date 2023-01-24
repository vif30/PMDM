package com.viizfo.youtubewithoutapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viizfo.youtubewithoutapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var youtubeVideos: MutableList<YouTubeVideos> = mutableListOf<YouTubeVideos>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/KyJ71G2UxTQ\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/y8Rr39jKFKU\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8Hg1tqIwIfI\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uhQ7mh_o_cM\" frameborder=\"0\" allowfullscreen></iframe>"))

        val videoAdapter = VideoAdapter(youtubeVideos)

        binding.rvVideoList.adapter = videoAdapter
    }
}