package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SingleAsteroid::class], version = 1, exportSchema = false)

abstract class AsteroidsDatabase : RoomDatabase() {

        abstract val asteroidDatabaseDao: AsteroidDatabaseDao

        companion object {

            @Volatile
            private var INSTANCE: AsteroidsDatabase? = null

            fun getInstance(context: Context): AsteroidsDatabase {
                synchronized(this) {
                    var instance = INSTANCE

                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AsteroidsDatabase::class.java,
                            "asteroid_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }