package com.example.webunihw_eatingplaces.network.apis

import com.example.webunihw_eatingplaces.model.auth.LoginResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthAPI {
    @FormUrlEncoded
    @POST("/auth/login/")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResult>
}