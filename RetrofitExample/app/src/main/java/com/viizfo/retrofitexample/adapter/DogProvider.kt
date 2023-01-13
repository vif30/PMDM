package com.viizfo.retrofitexample.adapter

import com.viizfo.retrofitexample.provider.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dog.ceo/api/breed/"
object DogProvider {

    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getDogsByBreed(breed:String):List<String> = withContext(Dispatchers.IO){
        var dogs = emptyList<String>()
        val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$breed/images")

        if(call.isSuccessful){
            val puppies = call.body()
            val images = puppies?.images ?: emptyList()

            dogs = dogs + images
        }

        dogs
    }
}