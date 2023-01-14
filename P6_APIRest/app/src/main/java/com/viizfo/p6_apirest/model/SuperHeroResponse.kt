package com.viizfo.p6_apirest.model



data class SuperHeroResponse(
    var response: String,
    var id: Int,
    var name: String,
    var powerstats: List<String>

)
