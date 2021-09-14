package com.example.nasaassignment.data.remote

import com.example.nasaassignment.data.entity.rover.RoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("rovers/{name}/photos?sol=37&api_key=8WOqaP9QYJMXntVz3lTb6slKdt62ZF1nJkFLR6fj")
    suspend fun getRoverByName(
        @Path("name") name: String,
        @Query("camera") camera: String?,
        @Query("page") page: Int?
    ): Response<RoverResponse>

}