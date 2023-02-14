package com.viizfo.test2ev.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getWord (@Url url: String): Response<List<APIResponse>>

}