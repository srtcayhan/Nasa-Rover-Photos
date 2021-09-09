package com.example.nasaassignment.ui.rovers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasaassignment.data.entity.rover.Photo
import com.example.nasaassignment.databinding.RoverCardBinding
import com.example.nasaassignment.ui.photodetails.IPhotoOnClick
import com.example.nasaassignment.ui.rovers.opportunity.OpportunityFragmentDirections

class RoversAdapter : RecyclerView.Adapter<RoversAdapter.RoversViewHolder>() {

    private var photoList = ArrayList<Photo>()

    private var listener: IPhotoOnClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoversAdapter.RoversViewHolder {
        val binding = RoverCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RoversViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoversViewHolder, position: Int) {
        val rover = photoList[position]

        Glide.with(holder.binding.roverCardImage.context)
            .load(rover.img_src)
            .into(holder.binding.roverCardImage)

        holder.binding.roverCardView.setOnClickListener{
            listener?.onClick(rover)
        }
    }

    override fun getItemCount(): Int = photoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setPhotoList(photoList: ArrayList<Photo>?) {
        photoList?.let {
            this.photoList = photoList
            this.notifyDataSetChanged()
        }
    }

    fun addListener(listener: IPhotoOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }


    inner class RoversViewHolder(val binding: RoverCardBinding) :
        RecyclerView.ViewHolder(binding.root)

}