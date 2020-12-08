package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class Cart (var id: String?, var name_cat: String?,var price:Int ,var img:String?,  var size:String?, var quantity:Int):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()


    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name_cat)
        parcel.writeInt(price)
        parcel.writeString(img)
        parcel.writeString(size)
        parcel.writeInt(quantity)

    }

    override fun describeContents(): Int {
        return 0
    }
    constructor():this("","",0," ",  "" ,0)
    constructor( name:String ,  price: Int , img:String?,  size:String, quantity: Int):this ("",name, price, img , size, quantity )
    constructor( name:String ,  price: Int , img:String?,  size:String):this ("",name, price, img , size ,0 )


    companion object CREATOR : Parcelable.Creator<Cart> {
        override fun createFromParcel(parcel: Parcel): Cart {
            return Cart(parcel)
        }

        override fun newArray(size: Int): Array<Cart?> {
            return arrayOfNulls(size)
        }
    }
    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result["name"] = name_cat!!
        result["price"]=price
        result["img"]=img!!
        result["size"]=size!!
        result["quantity"]=quantity



        return result
    }
}