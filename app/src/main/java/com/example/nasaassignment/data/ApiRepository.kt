package com.example.nasaassignment.data

import com.example.nasaassignment.data.remote.RemoteDataSource
import com.example.nasaassignment.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {

    fun getRoverByName(name: String, camera: String?, page: Int?) = performNetworkOperation {
        remoteDataSource.getRoverByName(name, camera, page)
    }
}