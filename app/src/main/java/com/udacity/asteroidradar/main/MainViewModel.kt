package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NeoWsAPI
import com.udacity.asteroidradar.database.getInstance
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

enum class AsteroidsFilter{ SHOW_TODAY, SHOW_SAVED, SHOW_WEEK }

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val database = getInstance(application)

    private val asteroidRepository = AsteroidRepository(database)

    private val asteroidsFilter = MutableLiveData<AsteroidsFilter>()

    private val _imgOfToday = MutableLiveData<PictureOfDay>()
    val imgOfToday: LiveData<PictureOfDay>
            get() = _imgOfToday

    private val _navigateToDetailFragment = MutableLiveData<Asteroid>()
    val navigateToDetailFragment: LiveData<Asteroid>
            get() = _navigateToDetailFragment



    init {

        asteroidsFilter.value = AsteroidsFilter.SHOW_TODAY
        fetchAsteroidData()
    }


    val asteroids = Transformations.switchMap(asteroidsFilter){
        asteroidRepository.getAsteroidsFiltered(it)
    }

    private fun fetchAsteroidData(){

        viewModelScope.launch {

            try {

                asteroidRepository.refreshAsteroids()

                _imgOfToday.value = NeoWsAPI.retrofitService.getImgOfToday(BuildConfig.API_KEY)

            }catch (e: Exception){

            }
        }
    }



    fun onNavigateToAsteroidDetail(asteroid: Asteroid){
        _navigateToDetailFragment.value = asteroid
    }

    fun onNavigationToAsteroidDetailComplete(){
        _navigateToDetailFragment.value = null
    }

    fun updateFilter(filter: AsteroidsFilter){

        asteroidsFilter.value = filter
    }

}