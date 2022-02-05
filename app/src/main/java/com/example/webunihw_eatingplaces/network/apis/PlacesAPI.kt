package com.example.webunihw_eatingplaces.network.apis

import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface PlacesAPI {

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
    ): ResponseBody

    @GET("/places/")
    suspend fun getPlaces(): Response<PlaceListResult>

    @FormUrlEncoded
    @POST("/places/create")
    suspend fun uploadPlace(
        @Field("fullName") fullName: String,
        @Field("postalCode") postalCode: String,
        @Field("city") city: String,
        @Field("address") address: String,
        @Field("lat") lat: String,
        @Field("lon") lon: String,
        @Field("categories") categories: String,
        @Field("image") image: String,
        @Field("description") description: String,
    ): ResponseBody

//    @Multipart
//    @POST("/places/create")
//    suspend fun uploadPlace(
//        @Part("fullName") fullName: ResponseBody,
//        @Part("postalCode") postalCode: ResponseBody,
//        @Part("city") city: ResponseBody,
//        @Part("address") address: ResponseBody,
//        @Part("lat") lat: ResponseBody,
//        @Part("lon") lon: ResponseBody,
//        @Part("categories") categories: ResponseBody,
//        @Part("image") image: ResponseBody,
//        @Field("description") description: ResponseBody
//    ): ResponseBody


}
