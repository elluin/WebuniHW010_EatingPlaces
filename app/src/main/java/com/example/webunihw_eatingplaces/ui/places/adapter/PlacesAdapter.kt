package com.example.webunihw_eatingplaces.ui.places.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webunihw_eatingplaces.databinding.ItemPlaceBinding
import com.example.webunihw_eatingplaces.model.places.Place

class PlacesAdapter(private val context: Context,private val places: List<Place>) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>(){
   // lateinit var context: Context
    //var places = mutableListOf<Place>()

    lateinit var currentPlaceId: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlaceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val place: Place = places[position]
        //val place = places.get(holder.adapterPosition)
        val author = place.uploadedBy?.uploaderUserName
        val address = place.city + ", " + place.address
        var category = place.categories

        holder.tvAuthor.text = author
        holder.tvTitle.text = place.fullName
        holder.tvAddress.text = address
        holder.tvCategory.text = category

        if (place.image?.isNotEmpty() == true) {
            holder.ivPhoto.visibility = View.VISIBLE
            Glide.with(context).load(place.image).into(holder.ivPhoto)
        }
    }


    inner class ViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvAuthor = binding.textviewAuthor
        var tvTitle = binding.textviewTitle
        var tvAddress = binding.textviewAddress
        var ivPhoto = binding.imageviewPhoto
        var tvCategory = binding.textviewCategory
    }
}