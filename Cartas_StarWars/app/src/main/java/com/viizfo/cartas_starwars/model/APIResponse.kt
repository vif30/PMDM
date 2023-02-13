package com.viizfo.cartas_starwars.model

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String)