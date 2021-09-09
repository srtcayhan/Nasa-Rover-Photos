package com.example.nasaassignment.data.entity.rover

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RoverResponse(
    val photos: ArrayList<Photo>
)

@Parcelize
data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
) : Parcelable

@Parcelize
data class Camera(
    val full_name: String,
    val id: Int,
    val name: String,
    val rover_id: Int
) : Parcelable

@Parcelize
data class Rover(
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val name: String,
    val status: String
) : Parcelable