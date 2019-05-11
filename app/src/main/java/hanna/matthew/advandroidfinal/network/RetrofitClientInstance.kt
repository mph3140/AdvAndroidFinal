package hanna.matthew.advandroidfinal.network

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://my-json-server.typicode.com/mph3140/FakeDBForFinal/"

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