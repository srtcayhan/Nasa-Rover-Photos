package com.example.nasaassignment.ui.photodetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.nasaassignment.R
import com.example.nasaassignment.data.entity.rover.Photo
import com.example.nasaassignment.databinding.FragmentPhotoDetailsBinding


class PhotoDetailsFragment(val photo: Photo) : DialogFragment() {

    override fun getTheme() = R.style.RoundedCornersDialog

    private lateinit var binding: FragmentPhotoDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            captureDate.text = photo.earth_date
            cameraName.text = photo.camera.name
            roverName.text = photo.rover.name
            roverStatus.text = photo.rover.status
            launchDate.text = photo.rover.launch_date
            landingDate.text = photo.rover.landing_date

            Glide.with(requireContext())
                .load(photo.img_src)
                .into(photoImageView)
        }
    }

}