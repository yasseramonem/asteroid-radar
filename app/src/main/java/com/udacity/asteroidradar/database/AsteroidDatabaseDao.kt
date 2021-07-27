package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AsteroidDatabaseDao {

    @Insert
    suspend fun insert(asteroid: AsteroidEntity)

    @Update
    suspend fun update(asteroid: AsteroidEntity)

    @Query("SELECT * FROM asteroids_table ORDER BY id DESC")
    fun getAllAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids_table ORDER BY closeApproachDate DESC LIMIT 1")
    suspend fun getAsteroid(): AsteroidEntity?
}