package hanna.matthew.advandroidfinal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
//    fun listRepos(@Path("user") user: String): Call<List<Repo>>
    @GET("/collections")
    fun getAllCollections(): Call<List<Collection>>

    @GET("/shoes")
    fun getAllShoes(): Call<List<Shoe>>
}
