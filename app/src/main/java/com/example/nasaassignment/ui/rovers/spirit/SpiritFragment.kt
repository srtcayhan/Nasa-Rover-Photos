package com.example.nasaassignment.ui.rovers.spirit

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
import com.example.nasaassignment.databinding.FragmentSpiritBinding
import com.example.nasaassignment.ui.IMenuOnClick
import com.example.nasaassignment.ui.MainActivity
import com.example.nasaassignment.ui.photodetails.IPhotoOnClick
import com.example.nasaassignment.ui.photodetails.PhotoDetailsFragment
import com.example.nasaassignment.ui.rovers.RoversAdapter
import com.example.nasaassignment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpiritFragment : Fragment() ,IPhotoOnClick, IMenuOnClick {

    private lateinit var binding: FragmentSpiritBinding
    private val viewModel: SpiritViewModel by viewModels()


    private var adapter = RoversAdapter()

    private var roversAdapter: RoversAdapter = RoversAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpiritBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.spiritRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.spiritRecyclerView.adapter = roversAdapter

        (activity as MainActivity).addListener(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRoverByName("spirit",camera = null,page=null).observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
//                    Log.d(ContentValues.TAG, "onCreate: "+response.data.toString())
                    viewModel.photoList = response.data?.photos
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
        binding.spiritRecyclerView.adapter = adapter

    }
    override fun onClick(photo: Photo) {
        Log.d(ContentValues.TAG, "onClick: sadasd")
        setPhotoDetails(photo)
    }

    private fun setPhotoDetails(photo: Photo){
        val popupdialog = PhotoDetailsFragment(photo)
        popupdialog.show(requireActivity().supportFragmentManager,"PopUpDialog")
    }

    override fun onMenuClick(camera: String) {

        Log.d("TAG", "onMenuClick: " + camera)
        viewModel.getRoverByName("spirit", camera,page = null).observe(viewLifecycleOwner, { response ->

            when (response.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
//                    Log.d(ContentValues.TAG, "onCreate: "+response.data.toString())
                    viewModel.photoList = response.data?.photos
                    // if its not null add all
                    setPhotoList(viewModel.photoList)
                    Log.d("TAG", "onMenuClick: " + viewModel.photoList?.size)
                }
                Resource.Status.ERROR -> {
                    Log.d(ContentValues.TAG, "getRestaurants: ${response.message}")
                }
            }

        })
    }
}