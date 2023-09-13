package edu.vavali.cupcakes

import android.os.Parcel
import android.os.Parcelable

data class CupcakeOrder(
    val numCupcakes: Int = 0,
    val flavor: String? = " ",
    val price: Double = 0.0,
    val date: String? = " "

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(numCupcakes)
        parcel.writeString(flavor)
        parcel.writeDouble(price)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CupcakeOrder> {
        override fun createFromParcel(parcel: Parcel): CupcakeOrder {
            return CupcakeOrder(parcel)
        }

        override fun newArray(size: Int): Array<CupcakeOrder?> {
            return arrayOfNulls(size)
        }
    }
}
