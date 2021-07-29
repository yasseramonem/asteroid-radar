package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidEntity::class], version = 1, exportSchema = false)
abstract class AsteroidsDatabase : RoomDatabase() {

    abstract val asteroidDatabaseDao: AsteroidDatabaseDao
}

private lateinit var INSTANCE: AsteroidsDatabase

fun getInstance(context: Context): AsteroidsDatabase {
    synchronized(AsteroidsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidsDatabase::class.java,
                "asteroid_database").build()
        }
        return INSTANCE
    }
}
