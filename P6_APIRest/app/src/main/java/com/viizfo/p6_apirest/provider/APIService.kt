package com.viizfo.p6_apirest.provider

import com.viizfo.p6_apirest.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getSuperHeroById(@Url url:String): Response<ApiResponse>
}