package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class SingleAsteroid (

    @PrimaryKey
    @Json(name = "id") var id: Long,

    @ColumnInfo
    @Json(name = "name") var codename: String,

    @ColumnInfo
    @Json(name = "close_approach_date") var closeApproachDate: String,

    @ColumnInfo
    @Json(name = "absolute_magnitude_h") var absoluteMagnitude: Double,

    @ColumnInfo
    @Json(name = "estimated_diameter_min") var estimatedDiameter: Double,

    @ColumnInfo
    @Json(name = "kilometers_per_second") var relativeVelocity: Double,

    @ColumnInfo
    @Json(name = "kilometers") var distanceFromEarth: Double,

    @ColumnInfo
    @Json(name = "is_potentially_hazardous_asteroid") var isPotentiallyHazardous: Boolean)