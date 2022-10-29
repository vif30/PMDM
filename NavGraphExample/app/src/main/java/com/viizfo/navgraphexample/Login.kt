package com.viizfo.navgraphexample

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField

data class Login(
    var usr:String?,
    var pass:String?,
    var email:String?,
    var birthDate:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(usr)
        parcel.writeString(pass)
        parcel.writeString(email)
        parcel.writeString(birthDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Login> {
        override fun createFromParcel(parcel: Parcel): Login {
            return Login(parcel)
        }

        override fun newArray(size: Int): Array<Login?> {
            return arrayOfNulls(size)
        }
    }

    constructor() : this("","","","")
}