package com.example.nasaassignment.ui.rovers.curiosity

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasaassignment.data.entity.rover.Photo
import com.example.nasaassignment.databinding.FragmentCuriosityBinding
import com.example.nasaassignment.ui.photodetails.IPhotoOnClick
import com.example.nasaassignment.ui.photodetails.PhotoDetailsFragment
import com.example.nasaassignment.ui.rovers.RoversAdapter
import com.example.nasaassignment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuriosityFragment : Fragment() , IPhotoOnClick{


    private lateinit var binding: FragmentCuriosityBinding
    private val viewModel: CuriosityViewModel by viewModels()


    private var adapter = RoversAdapter()

    private var roversAdapter: RoversAdapter = RoversAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCuriosityBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.curiosityRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.curiosityRecyclerView.adapter = roversAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRoverByName("curiosity").observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
//                    Log.d(ContentValues.TAG, "onCreate: "+response.data.toString())
                    viewModel.photoList = response.data?.photos
                    // if its not null add all
                    setPhotoList(viewModel.photoList)
                }
                Resource.Status.ERROR -> {
                    Log.d(ContentValues.TAG, "getRestaurants: ${response.message}")
                }
            }

        })
    }

    private fun setPhotoList(photoList: ArrayList<Photo>?) {

        adapter.addListener(this)
        adapter.setPhotoList(photoList)
        binding.curiosityRecyclerView.adapter = adapter

    }
    override fun onClick(photo: Photo) {
        Log.d(ContentValues.TAG, "onClick: sadasd")
        setPhotoDetails(photo)
    }

    private fun setPhotoDetails(photo: Photo){
        val popupdialog = PhotoDetailsFragment(photo)
        popupdialog.show(requireActivity().supportFragmentManager,"PopUpDialog")
    }

}