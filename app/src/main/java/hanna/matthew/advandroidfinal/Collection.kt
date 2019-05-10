package hanna.matthew.advandroidfinal

import com.google.gson.annotations.SerializedName

class Collection(
    @field:SerializedName("collectionId") var collectionId: Int?,
    @field:SerializedName("name") var name: String?
)/*{
    fun Collection(collectionId: Int?, name: String?) {
        this.collectionId = collectionId
        this.name = name
    }
}*/
