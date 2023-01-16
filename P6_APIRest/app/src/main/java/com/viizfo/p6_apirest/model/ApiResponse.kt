package com.viizfo.p6_apirest.model

data class ApiResponse(
    var response: String,
    var results: List<SuperHero>,
    /*var id: String,
    var name: String,
    var image: Image,
    var powerstats: Stats*/
)
