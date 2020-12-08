package com.example.shopingonline.model

import android.os.Parcel
import android.os.Parcelable

data class UserRE (var id:String?,var email:String?,  var password:String?):
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(password)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor() : this("", "", " ")
    constructor( email: String, password: String) : this(
        "",
        email,
        password
    )

    companion object CREATOR : Parcelable.Creator<UserRE> {
        override fun createFromParcel(parcel: Parcel): UserRE {
            return UserRE(parcel)
        }

        override fun newArray(size: Int): Array<UserRE?> {
            return arrayOfNulls(size)
        }
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result["email"] = email!!
        result["password"] = password!!
        return result
    }

}