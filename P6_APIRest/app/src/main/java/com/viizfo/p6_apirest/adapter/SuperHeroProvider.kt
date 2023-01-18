package com.viizfo.p6_apirest.adapter

import com.viizfo.p6_apirest.model.SuperHero
import com.viizfo.p6_apirest.provider.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://superheroapi.com/api/10223412216002446/search/"

object SuperHeroProvider {
    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Function to search a superhero by name and returns a list<SuperHero> with all the matches
    suspend fun getSuperHeroByName(name:String):List<SuperHero?> = withContext(Dispatchers.IO){
        var superheroe: List<SuperHero?> = emptyList()
        val call = getRetrofit().create(APIService::class.java).getSuperHeroByName("$name")
        if(call.isSuccessful){
            val hero = call.body()
            val superheroes = hero?.results ?: emptyList()
            superheroe = superheroe + superheroes
        }
        superheroe
    }

}