package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.*
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.main.AsteroidsFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val asteroidsDatabase: AsteroidsDatabase) {


    fun getAsteroidsFiltered(filter: AsteroidsFilter): LiveData<List<Asteroid>> {
        return Transformations.map(when (filter) {
            AsteroidsFilter.SHOW_TODAY ->
                asteroidsDatabase.asteroidDatabaseDao.getTodayAsteroids(getCurrentDate())
            AsteroidsFilter.SHOW_SAVED ->
                asteroidsDatabase.asteroidDatabaseDao.getAllSavedAsteroids()
            else -> asteroidsDatabase.asteroidDatabaseDao.getAsteroidsFromTodayOnwards(getCurrentDate())
        })
        {
            it.asDomainModel()
        }
    }


    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO){
            val asteroidList = parseAsteroidsJsonResult(
                JSONObject(
                    NeoWsAPI.retrofitService.getAsteroids(BuildConfig.API_KEY)
                )
            )
            asteroidsDatabase.asteroidDatabaseDao.insertAll(*asteroidList.asDatabaseModel())
        }
    }

}

