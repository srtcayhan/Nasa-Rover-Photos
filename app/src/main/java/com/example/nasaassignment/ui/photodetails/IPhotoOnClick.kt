package com.example.nasaassignment.ui.photodetails

import com.example.nasaassignment.data.entity.rover.Photo

interface IPhotoOnClick {
    fun onClick(photo: Photo)
}