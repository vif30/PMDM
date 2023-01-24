package com.viizfo.videofrominternet


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.viizfo.videofrominternet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val urlVideo = "https://sdram58.github.io/apuntesPMDM/unidades2223/UD9/assets/bola_drac.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


        val uri: Uri = Uri.parse(urlVideo)
        with(binding.vvVideo){
            setMediaController(MediaController(this@MainActivity))
            setVideoURI(uri)
            requestFocus()
            setOnPreparedListener { mp ->
                mp.isLooping = true
                binding.vvVideo.start()
            }
        }



    }
}