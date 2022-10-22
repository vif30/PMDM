package com.viizfo.p2_master_detail_series

import android.content.Context

fun String.getImage(context: Context):Int{
    val imageName = this.split(".")[0] //Wihtout extension jpg, png...

    return context.resources.getIdentifier(
        imageName,
        "drawable",
        context.packageName
    )
}