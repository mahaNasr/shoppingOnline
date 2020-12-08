package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class Homegrid(var id:Int, var name :String?, var image:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Homegrid> {
        override fun createFromParcel(parcel: Parcel): Homegrid {
            return Homegrid(parcel)
        }

        override fun newArray(size: Int): Array<Homegrid?> {
            return arrayOfNulls(size)
        }
    }
}