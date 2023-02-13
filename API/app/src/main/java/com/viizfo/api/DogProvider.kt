package com.viizfo.api


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://akabab.github.io/starwars-api/api/"
object DogProvider {

    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    suspend fun getDogsByBreed(breed:String):List<DogsResponse> = withContext(Dispatchers.IO){
        var dogs = mutableListOf<DogsResponse>()
        val call = getRetrofit().create(APIService::class.java).getPersonajeById("id/$breed.json")
        if(call.isSuccessful){
            val puppies = call.body()
            val personaje = puppies?.let { DogsResponse(it.id, puppies.name, puppies.image ) }
            if (personaje != null) {
                dogs.add(personaje)
            }
        }
        dogs
    }
    suspend fun getAllDogs(breed: String):List<DogsResponse> = withContext(Dispatchers.IO){
        var dogs = mutableListOf<DogsResponse>()
        val call = getRetrofit().create(APIService::class.java).getAllPersonajes("$breed.json")
        if(call.isSuccessful){
            val puppies = call.body()
            val personaje = puppies?.results ?: emptyList()
            dogs += personaje
        }
        dogs
    }
}