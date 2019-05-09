package hanna.matthew.advandroidfinal

import com.google.gson.annotations.SerializedName

data class Collection(
    @field:SerializedName("collectionId") var collectionId: Int?,
    @field:SerializedName("name") var name: String?

)
