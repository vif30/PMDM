package com.viizfo.posibleexamen

import android.content.Context

fun String.getImage(context: Context):Int{
    val imageName = this.split(".")[0]      //Remove the extension
    return context.resources.getIdentifier(           //Return the image and the context
        imageName,
        "drawable",
        context.packageName
    )
}