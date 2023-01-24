package com.viizfo.mediaplayerexample

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.viizfo.mediaplayerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var mediaPlayer: MediaPlayer? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
    }

    fun music(view: View) {
        when (view.id) {
            R.id.btnPlay -> {
                // Check if mediaPlayer is null. If true, we'll instantiate the MediaPlayer object
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.bola_drac_intro)
                }
                // Then, register OnCompletionListener that calls a user supplied callback method onCompletion() when
                // looping mode was set to false to indicate playback is completed.
                mediaPlayer?.setOnCompletionListener { // Here, call a method to release the MediaPlayer object and to set it to null.
                    stopMusic()
                }
                // Next, call start() method on mediaPlayer to start playing the music.

                mediaPlayer?.start()
            }
            R.id.btnPause -> if (mediaPlayer != null) {
                // Here, call pause() method on mediaPlayer to pause the music.
                mediaPlayer?.pause()
            }
            R.id.btnStop -> if (mediaPlayer != null) {
                // Here, call stop() method on mediaPlayer to stop the music.
                mediaPlayer?.stop()
                // Call stopMusic() method
                stopMusic()
            }
        }
    }

    private fun stopMusic() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    // Call stopMusic() in onStop() overridden method as well.
    override fun onStop() {
        super.onStop()
        stopMusic()
    }
}