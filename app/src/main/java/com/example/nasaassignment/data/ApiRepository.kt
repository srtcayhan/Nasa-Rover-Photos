package com.example.nasaassignment.data

import com.example.nasaassignment.data.remote.RemoteDataSource
import com.example.nasaassignment.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {

    fun getRoverByName(name: String) = performNetworkOperation {
        remoteDataSource.getRoverByName(name)
    }

    fun getManifestByName(name: String) = performNetworkOperation {
        remoteDataSource.getManifestByName(name)
    }

    fun getPhotosByCamera(name: String, camera: String) = performNetworkOperation {
        remoteDataSource.getPhotosByCamera(name,camera)
    }
}