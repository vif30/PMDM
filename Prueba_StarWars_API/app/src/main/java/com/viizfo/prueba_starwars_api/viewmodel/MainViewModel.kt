package com.viizfo.prueba_starwars_api.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viizfo.prueba_starwars_api.model.StarWarsData
import com.viizfo.prueba_starwars_api.service.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {


    val characterList = MutableLiveData<List<StarWarsData>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCharacters() {

        val response = repository.getAllCharacters()
        response.enqueue(object : Callback<List<StarWarsData>> {
            override fun onResponse(call: Call<List<StarWarsData>>, response: Response<List<StarWarsData>>) {
                characterList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<StarWarsData>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getCharacterById(id: Int) {
    }
}