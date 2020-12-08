package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class CategorList(var id:String? ,var name:String?,   var image:String? ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor():this(" ","", "")
    constructor(name: String?, image: String?  ):this (" ",name, image )

    companion object CREATOR : Parcelable.Creator<CategorList> {
        override fun createFromParcel(parcel: Parcel): CategorList {
            return CategorList(parcel)
        }

        override fun newArray(size: Int): Array<CategorList?> {
            return arrayOfNulls(size)
        }
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result["name"]= name!!
        result["image"]= image!!

        return result
    }
}
