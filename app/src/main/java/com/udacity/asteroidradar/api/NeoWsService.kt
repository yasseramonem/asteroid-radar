package com.udacity.asteroidradar.api


import com.udacity.asteroidradar.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


//    enum class MarsApiFilter(val value: String) {
//        SHOW_RENT("rent"),
//        SHOW_BUY("buy"),
//        SHOW_ALL("all")
//    }

//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
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

