package com.viizfo.cartas_starwars.provider

import com.viizfo.cartas_starwars.model.APIResponse
import com.viizfo.cartas_starwars.model.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://akabab.github.io/starwars-api/api/"
object APIProvider {
    //Creates an instance of Retrofit
    private fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    suspend fun getCharactersById(id:String):List<APIResponse> = withContext(Dispatchers.IO){
        var characters = mutableListOf<APIResponse>()
        val call = getRetrofit().create(APIService::class.java).getPersonajeById("id/$id.json")
        if(call.isSuccessful){
            val charac = call.body()
            val personaje = charac?.let { APIResponse(charac.id, charac.name, charac.image ) }
            if (personaje != null) {
                characters.add(personaje)
            }
        }
        characters
    }
}