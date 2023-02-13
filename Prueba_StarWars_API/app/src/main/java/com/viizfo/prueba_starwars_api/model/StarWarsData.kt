package com.viizfo.prueba_starwars_api.model

import com.google.gson.annotations.SerializedName

data class StarWarsData(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)