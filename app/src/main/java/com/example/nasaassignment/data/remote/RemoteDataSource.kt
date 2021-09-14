package com.example.nasaassignment.data.remote

import com.example.nasaassignment.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: APIService
) : BaseDataSource() {
    suspend fun getRoverByName(name: String, camera: String?, page: Int?) =
        getResult { apiService.getRoverByName(name, camera, page) }
}
