package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class Home (  var id:Int, var imag:Int ):Parcelable {
    constructor(parcel: Parcel) : this(
parcel.readInt(),
    parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(imag)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Home> {
        override fun createFromParcel(parcel: Parcel): Home {
            return Home(parcel)
        }

        override fun newArray(size: Int): Array<Home?> {
            return arrayOfNulls(size)
        }
    }
}