package com.example.webunihw_eatingplaces.ui.places.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webunihw_eatingplaces.databinding.ItemPlaceBinding
import com.example.webunihw_eatingplaces.model.places.Place
import com.example.webunihw_eatingplaces.ui.places.PlaceDetailsActivity

class PlacesAdapter(private val context: Context, private val places: List<Place>) :
    RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

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

        holder.cardview.setOnClickListener() {
            showDetails(place)
        }
    }


    fun showDetails(place: Place) {
        val intent = Intent(context, PlaceDetailsActivity::class.java)
        intent.putExtra("KEY_PLACE", place.placeId)
        context.startActivity(intent)
    }

    inner class ViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvAuthor = binding.textviewAuthor
        var tvTitle = binding.textviewTitle
        var tvAddress = binding.textviewAddress
        var ivPhoto = binding.imageviewPhoto
        var tvCategory = binding.textviewCategory
        var cardview = binding.cardView
    }


}