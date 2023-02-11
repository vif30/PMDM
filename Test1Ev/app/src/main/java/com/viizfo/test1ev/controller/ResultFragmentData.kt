package com.viizfo.test1ev.controller

import android.os.Parcel
import android.os.Parcelable

data class ResultFragmentData(
    val numCorrects: Int,
    val totalQuestions: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(numCorrects)
        parcel.writeInt(totalQuestions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultFragmentData> {
        override fun createFromParcel(parcel: Parcel): ResultFragmentData {
            return ResultFragmentData(parcel)
        }

        override fun newArray(size: Int): Array<ResultFragmentData?> {
            return arrayOfNulls(size)
        }
    }
}