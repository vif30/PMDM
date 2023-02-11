package com.viizfo.test1ev.model

data class Question(
    val question: String,
    val answers: List<String>,
    val image: String,
    val correct_answer: Int
)