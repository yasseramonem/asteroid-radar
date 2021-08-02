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


//Creating OkHttpClient to increase avoid Error: SocketTimeout following

private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .build()

//Creating an instance of Moshi to handle the ImageOfToday call
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

//Buidling Retrofit service using 2 converter factories Moshi & Scalar
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()


//Creating NeoWS Service Interface using @GET Annotation to fetch Asteroids Data as well as Image of Today
//the Asteroids Query is filtered by the START_DATE using a String Parameter will be added on the function call
//so that we can only get the new data from Today onwards
//Adding the API_KEY using a String Parameter will be added on function call as well
interface NeoWsService {

        @GET("neo/rest/v1/feed")
        suspend fun getAsteroids(  @Query("api_key" ) key: String ): String

        @GET("planetary/apod")
        suspend fun getImgOfToday( @Query("api_key" ) key: String ): PictureOfDay
}

//NeoWs API Object to be exposed to the rest of the application
object NeoWsAPI{
        val retrofitService: NeoWsService by lazy {
            retrofit.create(NeoWsService::class.java)
        }
}

//@Query("start_date") date: String,
