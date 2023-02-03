package com.catata.clienttwo.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

fun sendDelayedFunction(waitTime:Long, function:()->Unit){
    CoroutineScope(Dispatchers.Default).launch {
        delay(waitTime)
        function()
    }
}

fun getCurFormattedData():String{
    val sdf = SimpleDateFormat("dd/MM/yy hh:mm")
    return sdf.format(Date())
}