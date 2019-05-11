package hanna.matthew.advandroidfinal.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Shoe(
    var shoeId: Int?,
    var name: String?,
    var collectionId: Int?,
    var shoeImageURL: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(shoeId)
        parcel.writeString(name)
        parcel.writeValue(collectionId)
        parcel.writeString(shoeImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shoe> {
        override fun createFromParcel(parcel: Parcel): Shoe {
            return Shoe(parcel)
        }

        override fun newArray(size: Int): Array<Shoe?> {
            return arrayOfNulls(size)
        }
    }
}
