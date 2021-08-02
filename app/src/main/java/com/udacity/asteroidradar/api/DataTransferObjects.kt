package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.domain.Asteroid

//Creating a Helper function to be used within Repository to retrieve Asteroids Data from database
//and use it to update views with liveData
fun List<AsteroidEntity>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it. id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}


//Creating a helper function to be used within Repository to insert Asteroids data that was fetched
//via Repository into Database

fun ArrayList<Asteroid>.asDatabaseModel(): Array<AsteroidEntity> {
    return map {
        AsteroidEntity (
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
