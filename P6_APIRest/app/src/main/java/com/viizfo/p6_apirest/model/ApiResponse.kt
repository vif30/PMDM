package com.viizfo.p6_apirest.model

data class ApiResponse(
    var response: String,
    var results: List<SuperHero>,
)
//Expected response from the API
