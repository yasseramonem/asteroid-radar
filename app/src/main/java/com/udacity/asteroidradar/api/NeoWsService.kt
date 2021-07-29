package com.udacity.asteroidradar.api


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


//    enum class MarsApiFilter(val value: String) {
//        SHOW_WEEK_ASTEROIDS("rent"),
//        SHOW_TODAY_ASTEROIDS("buy"),
//        SHOW_SAVED_ASTEROIDS("all")
//    }

//Creating OkHttpClient to increase avoid Error: SocketTimeout following

private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

interface NeoWsService {

        @GET("neo/rest/v1/feed")
        suspend fun getAsteroids( @Query("api_key" ) key: String ): String

        @GET("planetary/apod")
        suspend fun getImgOfToday( @Query("api_key" ) key: String ): PictureOfDay
}

object NeoWsAPI{
        val retrofitService: NeoWsService by lazy {
            retrofit.create(NeoWsService::class.java)
        }
}

