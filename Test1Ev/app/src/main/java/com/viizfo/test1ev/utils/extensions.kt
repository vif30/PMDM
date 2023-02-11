package com.viizfo.test1ev.utils

import android.widget.ImageView

fun ImageView.setFromString(image: String){
    val imageName = if(image.isNullOrEmpty()) "no_image" else image.substringBeforeLast(".")
    val id = context.resources.getIdentifier(
        imageName,
        "raw",
        context.packageName
    )
    setImageResource(id)
}
