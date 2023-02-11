package com.viizfo.test1ev.controller

import android.os.Parcel
import android.os.Parcelable

data class QuestionnaireFragmentData(
    val isFirst: Boolean,
    val isLast: Boolean,
    val question: String,
    val image: String,
    val answers: List<String>,
    val numQuestions: Int,
    val totalQuestions: Int,
    val savedAnswer: Int
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.createStringArrayList()?: listOf(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isFirst) 1 else 0)
        parcel.writeByte(if (isLast) 1 else 0)
        parcel.writeString(question)
        parcel.writeString(image)
        parcel.writeStringList(answers)
        parcel.writeInt(numQuestions)
        parcel.writeInt(totalQuestions)
        parcel.writeInt(savedAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionnaireFragmentData> {
        override fun createFromParcel(parcel: Parcel): QuestionnaireFragmentData {
            return QuestionnaireFragmentData(parcel)
        }

        override fun newArray(size: Int): Array<QuestionnaireFragmentData?> {
            return arrayOfNulls(size)
        }
    }

}


