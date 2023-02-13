package com.viizfo.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPersonajeById (@Url url: String): Response<DogsResponse>
    @GET
    suspend fun getAllPersonajes(@Url url: String): Response<APIResponse>
}