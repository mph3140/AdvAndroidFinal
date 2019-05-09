package hanna.matthew.advandroidfinal

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://my-json-server.typicode.com/mph3140/FakeDBForFinal/"

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
//            val service = retrofit!!.create(GitHubService::class.java)
            return retrofit
        }
}