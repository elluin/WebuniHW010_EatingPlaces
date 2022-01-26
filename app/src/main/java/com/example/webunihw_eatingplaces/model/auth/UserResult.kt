package com.example.webunihw_eatingplaces.model.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResult(
    val userId: String,
    val userName: String,
    val userEmail: String
)