package com.viizfo.api
import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String)