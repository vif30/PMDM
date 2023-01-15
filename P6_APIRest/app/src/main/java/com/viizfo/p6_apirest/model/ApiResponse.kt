package com.viizfo.p6_apirest.model

data class ApiResponse(
    var response: String,
    var id: String,
    var name: String,
    var url: List<String>
)
