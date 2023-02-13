package com.viizfo.prueba_starwars_api.service

/*
 * Repositorio para manejar datos de API.
 * Solo recibe la instancia del retrofit.
 * De la respuesta se encarga el ViewModel
 */
class MainRepository constructor(private val retrofitService: StarWarsApiService) {

    fun getAllCharacters() = retrofitService.getAllCharacters()

    fun getCharacterById(id: String) = retrofitService.getCharacterById(id)

}