package com.viizfo.p6_apirest.model

import com.google.gson.annotations.SerializedName
//all data that all superheroes have
data class SuperHero constructor(
    var id: String,
    var name: String,
    var biography: Biography,
    var image: Image
)

data class Biography(
    @SerializedName("full-name") var fullName: String,
    var aliases: List<String>,
    @SerializedName("first-appearance") var firstApp: String,
    var publisher: String
)

data class Image(
    var url: String
)