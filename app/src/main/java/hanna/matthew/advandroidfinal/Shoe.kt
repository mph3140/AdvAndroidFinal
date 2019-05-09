package hanna.matthew.advandroidfinal

import com.google.gson.annotations.SerializedName

data class Shoe(
    @field:SerializedName("shoeId") var shoeId: Int?,
    @field:SerializedName("name") var name: String?,
    @field:SerializedName("collectionId") var collectionId: Int?
)
