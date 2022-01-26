package com.example.webunihw_eatingplaces.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceListResult(
    val weather: List<PlaceResult>?
)

@JsonClass(generateAdapter = true)
data class PlaceResult(
    val id: String?,
    val fullName: String,
    val coord: Coord?,
    val postalCode: String?,
    val city: String?,
    val address: String,
    val categories: List<String>?,
    val uploadedBy: Uploader?,
    val uploadingDate: String?,
    val image: String?,
    val description: String?
)

@JsonClass(generateAdapter = true)
data class Coord(
    val lon: Double?,
    val lat: Double?
)

@JsonClass(generateAdapter = true)
data class Uploader(
    val uploaderId: String?,
    val uploaderUserName: String?
)