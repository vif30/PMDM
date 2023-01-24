package com.viizfo.videofromlocal

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController

import com.viizfo.videofromlocal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var mediaPlayer: MediaPlayer

    val mediaController:MediaController by lazy { MediaController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)


        val path = "android.resource://" + packageName + "/" + R.raw.bola_drac

        with(binding.vvVideo){


            mediaController.setAnchorView(this)

            setMediaController(mediaController)

            setVideoPath(path)
            //requestFocus()

            setOnPreparedListener { mp ->
                mp.isLooping = false
                mp.start()

            }
        }

        binding.btnPlay.setOnClickListener{
            binding.vvVideo.start()
        }

    }
}