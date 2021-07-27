package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.NeoWsAPI
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidEntity
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(): ViewModel() {

    private var singleAsteroid = MutableLiveData<AsteroidEntity?>()

//    private val allAsteroids = dataSource.getAllAsteroids()

    private val _asteroidsFromWeb = MutableLiveData<List<Asteroid>>()
    val asteroidsFromWeb: LiveData<List<Asteroid>>
            get() = _asteroidsFromWeb

    init {
//        initializeAsteroid()
        fetchAsteroidData()
    }

    private fun fetchAsteroidData(){

        viewModelScope.launch {

            try{

                var list = parseAsteroidsJsonResult(JSONObject(NeoWsAPI.retrofitService.getProperties()))

                Log.i("Asteroids List", list.toString())

                if (list.isNotEmpty()) {
                    _asteroidsFromWeb.value = list
                    Log.i("Asteroids List", list.toString())
                }

            } catch (e: Exception){

                Log.i("Crashed", e.toString())

            }

                }

        }

//    private fun initializeAsteroid(){
//        viewModelScope.launch {
//            singleAsteroid.value = getAsteroidFromDatabase()
//        }
//    }
//
//    private suspend fun getAsteroidFromDatabase(): AsteroidEntity?{
//        return dataSource.getAsteroid()
//    }
}