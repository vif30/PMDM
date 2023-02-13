package com.viizfo.prueba_starwars_api.service


import com.viizfo.prueba_starwars_api.model.StarWarsData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface StarWarsApiService {
    @GET("all.json")
    fun getAllCharacters(): Call<List<StarWarsData>>

    @GET
    fun getCharacterById(@Url url: String): Call<List<StarWarsData>>

    companion object {

        private var retrofitService: StarWarsApiService? = null

        fun getRetrofit() : StarWarsApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(StarWarsApiService::class.java)
            }
            return retrofitService!!
        }

    }

}

const val BASE_URL = "https://akabab.github.io/starwars-api/api/"

