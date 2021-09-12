package com.example.nasaassignment.data.remote

import com.example.nasaassignment.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) : BaseDataSource() {
    suspend fun getRoverByName(name: String) = getResult { apiService.getRoverByName(name) }

    suspend fun getManifestByName(name: String) = getResult { apiService.getManifestByName(name) }

    suspend fun getPhotosByCamera(name: String, camera: String) = getResult { apiService.getPhotosByCamera(name,camera) }
}
