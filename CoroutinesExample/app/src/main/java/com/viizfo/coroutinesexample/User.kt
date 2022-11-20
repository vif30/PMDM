package com.viizfo.coroutinesexample

data class User(
    val name: String,
    val friends: List<User> = emptyList()
)
