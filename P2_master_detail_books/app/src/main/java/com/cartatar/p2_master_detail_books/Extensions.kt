package com.cartatar.p2_master_detail_books

import android.content.Context

fun String.toIntId(context: Context):Int{
    val imageName = this.split(".")[0] //Wihtout extension jpg, png...

    return context.resources.getIdentifier(
        imageName,
        "drawable",
        context.packageName
    )
}