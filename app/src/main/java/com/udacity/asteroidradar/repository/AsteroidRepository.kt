package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants.KEY
import com.udacity.asteroidradar.api.NeoWsAPI
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.asDomainModel
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.domain.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val asteroidsDatabase: AsteroidsDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidsDatabase.asteroidDatabaseDao.getAllAsteroids()){
        it.asDomainModel()
    }

    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO){
            val asteroidList = parseAsteroidsJsonResult(JSONObject(NeoWsAPI.retrofitService.getAsteroids(KEY)))
            asteroidsDatabase.asteroidDatabaseDao.insertAll(*asteroidList.asDatabaseModel())
        }
    }

//    suspend fun refreshImage(){
//        withContext(Dispatchers.Default){
//            val imgOfToday = NeoWsAPI.retrofitService.getImgOfToday(KEY)
//        }
//    }
}

