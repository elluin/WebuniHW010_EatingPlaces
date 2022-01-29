package com.example.webunihw_eatingplaces.network.apis

import com.example.webunihw_eatingplaces.model.auth.LoginResult
import okhttp3.ResponseBody
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

    @FormUrlEncoded
    @POST("/user/registration/")
    suspend fun registration(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):ResponseBody

}





