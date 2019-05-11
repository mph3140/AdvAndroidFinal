package hanna.matthew.advandroidfinal.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Collection(
    var collectionId: Int?,
    var name: String?,
    var brandLogoURL: String?
): Serializable/*{
    fun Collection(collectionId: Int?, name: String?) {
        this.collectionId = collectionId
        this.name = name
    }
}*/
