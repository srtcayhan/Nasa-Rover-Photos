package com.example.nasaassignment.ui.rovers.curiosity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaassignment.data.entity.rover.Photo
import com.example.nasaassignment.databinding.FragmentCuriosityBinding
import com.example.nasaassignment.ui.IMenuOnClick
import com.example.nasaassignment.ui.MainActivity
import com.example.nasaassignment.ui.photodetails.IPhotoOnClick
import com.example.nasaassignment.ui.photodetails.PhotoDetailsFragment
import com.example.nasaassignment.ui.rovers.RoversAdapter
import com.example.nasaassignment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuriosityFragment : Fragment(), IPhotoOnClick, IMenuOnClick {

    private lateinit var binding: FragmentCuriosityBinding
    private val viewModel: CuriosityViewModel by viewModels()

    private var page = 1

    private var camera: String? = "fhaz"

    private var adapter = RoversAdapter()

    private var roversAdapter: RoversAdapter = RoversAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentCuriosityBinding.inflate(inflater, container, false)
        this.page = 1
        return binding.root
    }

    private fun initViews() {
        binding.curiosityRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.curiosityRecyclerView.adapter = roversAdapter

        (activity as MainActivity).addListener(this)

        if (camera == null) {
            binding.curiosityRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page++
                        fetchDataByPage(page, null)
                    }
                }
            })
        } else {
            binding.curiosityRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        page++
                        fetchDataByPage(page, camera)
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchDataByPage(page, null)
        this.camera = null

    }

    //paging photos by rover get page id to fetch data
    private fun fetchDataByPage(page: Int, camera: String?) {


        viewModel.getRoverByName("curiosity", camera, page = page)
            .observe(viewLifecycleOwner, { response ->

                when (response.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        viewModel.photoList = response.data?.photos
                        setPhotoList(viewModel.photoList)
                        Log.d("CuriosityFragment", "photoListSize: " + viewModel.photoList?.size)
                    }
                    Resource.Status.ERROR -> {
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
        setPhotoDetails(photo)
    }

    private fun setPhotoDetails(photo: Photo) {
        val popupdialog = PhotoDetailsFragment(photo)
        popupdialog.show(requireActivity().supportFragmentManager, "PopUpDialog")
    }

    //filter by camera and paging
    override fun onMenuClick(camera: String) {

        Log.d("CuriosityFragmentFilter", "CameraName: " + camera)

        this.camera = camera
        this.page = 1

        Log.d("CuriostiyFragmentFilter", "CameraValue: " + this.camera)
        viewModel.getRoverByName("curiosity", camera, page = 1)
            .observe(viewLifecycleOwner, { response ->

                when (response.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        viewModel.photoList = response.data?.photos
                        setPhotoList(viewModel.photoList)
                        Log.d(
                            "CuriosityFragmentFilter",
                            "photoListSize: " + viewModel.photoList?.size
                        )

                        if (viewModel.photoList?.size == 0) {
                            Toast.makeText(activity, "photo not found", Toast.LENGTH_LONG).show()
                        }
                    }
                    Resource.Status.ERROR -> {
                    }
                }

            })
    }
}