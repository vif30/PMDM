package com.viizfo.p4_livedata.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

typealias  OnOrder = (order:String) -> Unit

class Language {
    val random: Random = Random()
    var language: Job? = null
    var selectedLanguage = 0
    var repetitions = -1

    fun startLanguage(onOrder: OnOrder) {
        if (language == null || language!!.isCancelled || language!!.isCompleted) {
            language = CoroutineScope(Dispatchers.IO).launch {
                while (true) {
                    if (repetitions < 0) {
                        repetitions = 1     //number of times the language is repeated on the screen
                        selectedLanguage = random.nextInt(7) + 1    //randomly selected language
                    }
                    onOrder("LANGUAGE" + selectedLanguage + ":" + if (repetitions == 0) "CHANGE" else repetitions)  //information that we send to the viewmodel
                    repetitions--
                    delay(1000)     //number of milliseconds that every repetition lasts
                }
            }
        }
    }

    fun stopLanguage() {
        language?.let {
            if(it.isActive)
                it.cancel()
        }
        selectedLanguage = 0
        repetitions = -1
    }

    val orderLiveData: LiveData<String> = object: LiveData<String>(){
        override fun onActive() {
            super.onActive()
            startLanguage {
                    order -> postValue(order)
            }
        }
        override fun onInactive() {
            super.onInactive()
            stopLanguage()
        }
    }
}