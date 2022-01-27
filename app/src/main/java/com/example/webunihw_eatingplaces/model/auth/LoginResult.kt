package com.example.webunihw_eatingplaces.model.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResult(
    val userToken: String,
    val userId: String,
    val userEmail: String,
    val userName: String


)