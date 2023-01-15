package com.viizfo.p6_api_hp.adapter

import com.viizfo.p6_api_hp.model.HPResponse
import com.viizfo.p6_api_hp.provider.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://hp-api.onrender.com/api/"
object HPProvider {

    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getAllCharacters(): List<HPResponse>{
        var characters = emptyList<HPResponse>()
        val call = getRetrofit().create(APIService::class.java).getAllCharacters("characters")
        if(call.isSuccessful){

        }
    }

    suspend fun getDogsByBreed(breed:String):List<String> = withContext(Dispatchers.IO){
        var dogs = emptyList<String>()
        val call = getRetrofit().create(APIService::class.java).getAllCharacters("characters")

        if(call.isSuccessful){
            val puppies = call.body()
            val images = puppies?.images ?: emptyList()

            dogs = dogs + images
        }

        dogs
    }
}