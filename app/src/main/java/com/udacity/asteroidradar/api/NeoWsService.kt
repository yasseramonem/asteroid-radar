package com.udacity.asteroidradar.api


import com.udacity.asteroidradar.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


//    enum class MarsApiFilter(val value: String) {
//        SHOW_RENT("rent"),
//        SHOW_BUY("buy"),
//        SHOW_ALL("all")
//    }

//Creating OkHttpClient to increase avoid Error: SocketTimeout following

private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()

interface NeoWsService {

        @GET("feed?api_key=vskR8d9nukrAdgaMe4OyDYK2ywoxJsGLZGMhRc4G")
        suspend fun getProperties():
                String
}

object NeoWsAPI{
        val retrofitService: NeoWsService by lazy {
            retrofit.create(NeoWsService::class.java)
        }
}

