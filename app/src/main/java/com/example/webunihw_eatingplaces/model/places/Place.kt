package com.example.webunihw_eatingplaces.model.places

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Place(
    val image: String?,
    val placeId: String?,
    val fullName: String,
    val coord: Coord?,
    val postalCode: String?,
    val city: String?,
    val address: String,
    val categories: String,
    val uploadedBy: Uploader?,
    val uploadingDate: String?,

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