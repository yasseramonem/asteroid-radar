package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.api.getCurrentDate
import com.udacity.asteroidradar.database.getInstance
import com.udacity.asteroidradar.repository.AsteroidRepository
import retrofit2.HttpException

//This Worker will be responsible fetching Asteroids Data once a day according to the constraints
// that is defined in the AsteroidsApplication
class RefreshAsteroidsWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

        // Getting an instance of database while passing in the context
        val database = getInstance(applicationContext)

        // Getting an instance of our Repository while passing the database
        val repository = AsteroidRepository(database)

        //Attempting to Fetch Asteroid Data from Repository & deleting previous data saved
        //if failed the worker will retry

        return try{
            repository.refreshAsteroids()
            database.asteroidDatabaseDao.deletePreviousData(getCurrentDate())
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }

}