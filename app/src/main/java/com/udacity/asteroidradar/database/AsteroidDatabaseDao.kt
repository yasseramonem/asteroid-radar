package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface AsteroidDatabaseDao {

    @Insert
    suspend fun insert(asteroid: SingleAsteroid)

    @Update
    suspend fun update(asteroid: SingleAsteroid)
}