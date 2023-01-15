package com.viizfo.p6_api_hp.provider

import com.viizfo.p6_api_hp.model.HPResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllCharacters(@Url url: String): Response<HPResponse>
}