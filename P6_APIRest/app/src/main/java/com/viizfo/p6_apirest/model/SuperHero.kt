package com.viizfo.p6_apirest.model

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

data class SuperHero constructor(
    var id: String,
    var name: String,
    //var powerstats: Stats,
    var biography: Biography,
    var image: Image
)

data class Biography(
    @SerializedName("full-name") var fullName: String,
    var aliases: List<String>,
    @SerializedName("first-appearance") var firstApp: String,
    var publisher: String
)

data class Stats(
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String,
)
data class Image(
    var url: String
)