package com.example.webunihw_eatingplaces.network.apis

import com.example.webunihw_eatingplaces.model.auth.UserResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AuthAPI {
    @GET("/data/2.5/weather")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<UserResult>
}