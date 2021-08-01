package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants.KEY
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NeoWsAPI
import com.udacity.asteroidradar.database.getInstance
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {



    private val _imgOfToday = MutableLiveData<PictureOfDay>()
    val imgOfToday: LiveData<PictureOfDay>
            get() = _imgOfToday

    private val _navigateToDetailFragment = MutableLiveData<Asteroid>()
    val navigateToDetailFragment: LiveData<Asteroid>
            get() = _navigateToDetailFragment


    private val database = getInstance(application)

    private val asteroidRepository = AsteroidRepository(database)

    init {

//        fetchImgOfToday()
        fetchAsteroidData()
    }

    val asteroidsFromWeb = asteroidRepository.asteroids

    private fun fetchAsteroidData(){

        viewModelScope.launch {

            try {
                asteroidRepository.refreshAsteroids()

                var img = NeoWsAPI.retrofitService.getImgOfToday(KEY)

                Log.i("ImageCrashed", img.toString())
                if(img.mediaType == "image") {
                    _imgOfToday.value = img
                }
            }catch (e: Exception){
                Log.i("Getting Data", "Something Happened")
            }
        }
    }

    fun onNavigateToAsteroidDetail(asteroid: Asteroid){
        _navigateToDetailFragment.value = asteroid
    }

    fun onNavigationToAsteroidDetailComplete(){
        _navigateToDetailFragment.value = null
    }

//    private fun fetchImgOfToday(){
//        viewModelScope.launch {
//            try {
//                var img = NeoWsAPI.retrofitService.getImgOfToday(KEY)
//                Log.i("ImageCrashed", img.toString())
//                if(img.mediaType == "image"){
//                    _imgOfToday.value = img
//                }
//            } catch (e: Exception){
//                Log.i("ImageCrashed", e.toString())
//            }
//        }
//    }
}