package hanna.matthew.advandroidfinal.network

import hanna.matthew.advandroidfinal.data.Collection
import hanna.matthew.advandroidfinal.data.Shoe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface GitHubService {
    @GET("https://my-json-server.typicode.com/mph3140/FakeDBForFinal/collections/")
    fun getAllCollections(): Call<List<Collection>>
//    fun getAllCollections(): (@Path("user") user: String): Call<List<Collection>>

    @GET("https://my-json-server.typicode.com/mph3140/FakeDBForFinal/shoes")
    fun getAllShoes(): Call<List<Shoe>>
    @GET("https://my-json-server.typicode.com/mph3140/FakeDBForFinal/shoes/{collectionId}/")
//    fun getAllCollection(): (@Path("collectionId") user: String): Call<List<Collection>>
    fun getCollectionOfShoes(@Path("collectionId") collectionId: Int): Call<List<Shoe>>

    @PUT("https://my-json-server.typicode.com/mph3140/FakeDBForFinal/shoes")
    fun addNewShoeToDB(): Call<Shoe>
}
