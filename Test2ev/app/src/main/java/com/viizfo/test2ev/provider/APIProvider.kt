package com.viizfo.test2ev.provider



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://webcarlos.com/restperenxisa/"
object APIProvider {
    //Creates an instance of Retrofit
    fun getRetrofit (): Retrofit {
        return Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}