package com.viizfo.test2ev

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.viizfo.test2ev.broadcasts.MyBroadcastReceiver
import com.viizfo.test2ev.broadcasts.MyBroadcastReceiver.Companion.ACTION_AIRPLANE_MODE
import com.viizfo.test2ev.broadcasts.MyBroadcastReceiver.Companion.MY_ACTION_RECEIVER_ACTION
import com.viizfo.test2ev.broadcasts.MyBroadcastReceiver.Companion.MY_ACTION_RECEIVER_EXTRA
import com.viizfo.test2ev.databinding.ActivityMainBinding
import com.viizfo.test2ev.model.APIResponse
import com.viizfo.test2ev.viewmodel.ViewModel
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var binding: ActivityMainBinding
    private val wordList = mutableListOf<APIResponse>()
    private lateinit var broadcastReceiver: MyBroadcastReceiver
    private val immutableList: List<String> = listOf("test","car","window","hello","word")
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        binding.svWord.setOnQueryTextListener(this)
        createBroadcast()
    }
    //Funcion para buscar la palabra en la api a traves del viewmodel
    private fun searchByWord(query:String){
        MainScope().launch {
            val viewmodel = ViewModelProvider(this@MainActivity)[ViewModel::class.java]
            if(query !in immutableList){
                showError("Invalid word")
            } else {
                val WORDLIST = viewmodel.getWords(query)
                if (WORDLIST.isNotEmpty()) {
                    wordList.clear()
                    wordList.addAll(WORDLIST)
                    binding.tvWord.text = wordList[0].word
                    binding.tvDefinition.text =
                        wordList!![0].meanings!![0].definitions[0].definition
                    if (wordList[0].audio != null) {
                        binding.btnListen.isEnabled = true
                        binding.btnListen.setOnClickListener {
                            playAudio(wordList[0].audio?.get(0)?.audio)
                            var like = false
                            var animation = Animation(binding.ivLike1, R.raw.sound_animation, like)
                        }
                    } else {
                        showError("Invalid word")
                    }
                } else {
                    showError("Invalid Word")
                }
            }
        }
    }
    //Funcion para sacar la animacion
    private fun Animation(
        imageView: LottieAnimationView,
        animation: Int,
        like: Boolean,
    ) : Boolean {
        if (!like) { //If like is not pushed starts lottie animation
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else { //If like was pushed the uncheck it with an animation
            //applying an alpha
            imageView.animate()
                .alpha(0f) //hide the image view in 200ms
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() { //Adding some listener
                    override fun onAnimationEnd(animator: Animator) {//when this animation finishes
                        imageView.alpha = 1f //reset alpha
                    }
                })
        }
        return !like //returns if like is checked or not
    }
    //Intento del broadcast
    private fun sendMessage() {
        sendBroadcast(makeIntent())
    }
    private fun makeIntent():Intent{
        val intent = Intent(MY_ACTION_RECEIVER_ACTION)
        intent.putExtra(MY_ACTION_RECEIVER_EXTRA, "Message to Broadcast Receiver")
        return intent
    }
    //Funcion para el searchview
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByWord(query.lowercase())
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
    return true
    }
    //Funcion para mostrar error
    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT) .show()
    }
    private fun createBroadcast(){
        //new MyBroadcastReceiver
        broadcastReceiver = MyBroadcastReceiver()
        //We make an intent filter with the actions that we want to manage.
        val filter = IntentFilter(MY_ACTION_RECEIVER_ACTION)
        filter.addAction(ACTION_AIRPLANE_MODE)
        //We register our broadcast receiver
        registerReceiver(broadcastReceiver,filter)
    }
    //Funcion para reproducir el audio
    private fun playAudio(url: String?) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(url)

        mediaPlayer.prepare()
        mediaPlayer.start()

    }
}