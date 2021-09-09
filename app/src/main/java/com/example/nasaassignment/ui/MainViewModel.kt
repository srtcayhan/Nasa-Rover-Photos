package com.example.nasaassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.nasaassignment.data.ApiRepository
import com.example.nasaassignment.data.entity.rover.RoverResponse
import com.example.nasaassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRoverByName(name: String): LiveData<Resource<RoverResponse>> {
        return apiRepository.getRoverByName(name)
    }
}