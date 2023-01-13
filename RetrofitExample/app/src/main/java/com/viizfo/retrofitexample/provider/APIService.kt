package com.viizfo.retrofitexample.provider

import com.viizfo.retrofitexample.model.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds (@Url url: String): Response<DogsResponse>
}