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

    /*suspend fun getSuperHeroById(id:String):SuperHero? = withContext(Dispatchers.IO){
        var superheroe: SuperHero? = null
        val call = getRetrofit().create(APIService::class.java).getSuperHeroById("$id")
        if(call.isSuccessful){
            val heroe = call.body()
            val name = heroe?.name.toString()
            val id = heroe?.id.toString()
            val image = heroe?.image
            val superheroe = image?.let { SuperHero(id, name, it) }
            //val image = heroe?.url ?: emptyList()
            return@withContext superheroe
        }
        superheroe
    }*/
    suspend fun getSuperHeroByName(name:String):List<SuperHero?> = withContext(Dispatchers.IO){
        var superheroe: List<SuperHero?> = emptyList()
        val call = getRetrofit().create(APIService::class.java).getSuperHeroById("$name")
        if(call.isSuccessful){
            val hero = call.body()
            val superheroes = hero?.results ?: emptyList()
            /*val name = hero?.name.toString()
            val id = hero?.id.toString()
            val image = hero?.image
            val superheroe = hero?.results
            //image?.let { SuperHero(id, name, image) }
            //val image = heroe?.url ?: emptyList()*/
            superheroe = superheroe + superheroes
        }
        superheroe
    }

}