package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class CategorView (var id:String? ,var name:String?,var name_cat:String?, var img:String? ,  var img2:String? , var price:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(name_cat)
        parcel.writeString(img)
        parcel.writeString(img2)
        parcel.writeInt(price)

    }

    override fun describeContents(): Int {
        return 0
    }

    constructor():this(" ","", "","","",0)
    constructor(name: String?, name_cat:String?,  img:String? ,   img2:String? ,  price:Int  ):this (" ",name,name_cat, img, img2, price )

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
        result["name_cat"]= name_cat!!
        result["img"]=img!!
        result["img2"]=img2!!
        result["price"]= price


        return result
    }
}
