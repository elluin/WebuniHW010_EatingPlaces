package com.example.webunihw_eatingplaces.model

import com.example.webunihw_eatingplaces.model.places.Place
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceListResult(
    val places: List<Place>?
)

