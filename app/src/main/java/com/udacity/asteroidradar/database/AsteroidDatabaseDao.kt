package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * FROM asteroidentity ORDER BY closeApproachDate ASC")
    fun getAllSavedAsteroids(): LiveData<List<AsteroidEntity>>

    //This query is Selecting Asteroids data from database based on it's closeApproachDate and
    // sorting data Ascending
    @Query("SELECT * FROM asteroidentity WHERE closeApproachDate >= :today ORDER BY closeApproachDate ASC")
    fun getAsteroidsFromTodayOnwards(today: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroidentity WHERE closeApproachDate = :today")
    fun getTodayAsteroids(today: String): LiveData<List<AsteroidEntity>>

    //Inserting the previously fetched Asteroid Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroidEntity: AsteroidEntity)


    //Using this Query will Delete all Asteroids Data before today
    @Query("DELETE FROM asteroidentity WHERE closeApproachDate < :today ")
    suspend fun deletePreviousData(today: String)

}