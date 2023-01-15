package com.viizfo.p6_apirest.adapter

import com.viizfo.p6_apirest.provider.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://superheroapi.com/api/10223412216002446/"

object SuperHeroProvider {
    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getSuperHeroById(id:String):List<String> = withContext(Dispatchers.IO){
        var superheroe = emptyList<String>()
        val call = getRetrofit().create(APIService::class.java).getSuperHeroById("$id/image")
        if(call.isSuccessful){
            val heroe = call.body()
            val name = heroe?.name.toString()
            //val image = heroe?.url ?: emptyList()
            superheroe = superheroe + name
        }
        superheroe
    }

}