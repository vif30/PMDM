package com.viizfo.p8_chata.model

//Data class for the Message
data class Message(
    var text: String,   //Text we send
    var date: String,   //Date the text was sended
    var owner: Int      //Owner of the message
)
