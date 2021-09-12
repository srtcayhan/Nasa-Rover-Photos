package com.example.nasaassignment.ui.rovers.opportunity

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nasaassignment.data.ApiRepository
import com.example.nasaassignment.data.entity.manifest.ManifestResponse
import com.example.nasaassignment.data.entity.rover.Photo
import com.example.nasaassignment.data.entity.rover.RoverResponse
import com.example.nasaassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpportunityViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    var photoList: ArrayList<Photo>? = null

    fun getRoverByName(name : String, camera: String?,page: Int?): LiveData<Resource<RoverResponse>> {
        return apiRepository.getRoverByName(name,camera,page)}

    fun getManifestByName(name : String): LiveData<Resource<ManifestResponse>> {
        return apiRepository.getManifestByName(name)}

}