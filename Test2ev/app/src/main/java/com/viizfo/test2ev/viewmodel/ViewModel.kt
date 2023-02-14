package com.viizfo.test2ev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.viizfo.test2ev.model.APIResponse
import com.viizfo.test2ev.model.APIService
import com.viizfo.test2ev.provider.APIProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//Viewmodel de la aplicacion para llamar a la API
class ViewModel(application : Application) : AndroidViewModel(application) {

    suspend fun getWords(id:String):List<APIResponse> = withContext(Dispatchers.IO) {
        var wordsList = mutableListOf<APIResponse>()
        val call = APIProvider.getRetrofit().create(APIService::class.java)
            .getWord("dictionary.php?word=$id")
        if (call.isSuccessful) {
            val WORD = call.body()
            for (i in WORD!!) {
                val word = WORD?.let { APIResponse(i.word, i.audio, i.meanings) }
                if (word != null) {
                    wordsList.add(word)
                }
            }
        }
        wordsList
    }
}