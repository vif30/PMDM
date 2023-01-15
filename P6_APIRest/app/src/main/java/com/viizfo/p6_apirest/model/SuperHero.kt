package com.viizfo.p6_apirest.model

import java.lang.reflect.Constructor

data class SuperHero constructor(
    var id: String,
    var name: String,
    var powerstats: Stats,
    var image: Image
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