package com.viizfo.test2ev.model

import com.google.gson.annotations.SerializedName
//Clase modelo de la respuesta de la API
data class APIResponse(
    @SerializedName("word") val word: String,
    @SerializedName("phonetics") val audio: List<Audio>?,
    @SerializedName("meanings") val meanings: List<Meanings>?
)

data class Audio(
    @SerializedName("audio") val audio: String
)
data class Meanings(
    @SerializedName("definitions") val definitions: List<Definition>
)
data class Definition(
    @SerializedName("definition") val definition: String
)
