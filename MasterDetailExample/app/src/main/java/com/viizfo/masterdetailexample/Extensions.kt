package com.viizfo.masterdetailexample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun String.toBitmap(context: Context): Bitmap {
    val imageName = this.split(".")[0]
    val id = context.resources.getIdentifier(
        imageName,
        "raw",
        context.packageName
    )

    val inputStream =context.resources.openRawResource(id)
    return  BitmapFactory.decodeStream(inputStream)
}
