package com.example.webunihw_eatingplaces.network.apis

import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.model.places.Place
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @GET("/places/view")
    suspend fun getPlaceDetail(
        @Query("placeId") placeId: String
    ): Response<Place>

    @FormUrlEncoded
    @POST("/places/create")
    suspend fun uploadPlace(
        @Field("fullName") fullName: String,
        @Field("postalCode") postalCode: String,
        @Field("city") city: String,
        @Field("address") address: String,
        @Field("lat") lat: Double,
        @Field("lon") lon: Double,
        @Field("categories") categories: String,
        @Field("image") image: String,
        @Field("description") description: String,
    ): ResponseBody

//    @Multipart
//    @POST("/places/create")
//    suspend fun uploadPlace(
//        @Part file: MultipartBody.Part?,
//        @Part("fullName") requestBody: RequestBody,
//        @Part("postalCode") postalCode: String,
//        @Part("city") city: String,
//        @Part("address") address: String,
//        @Part("lat") lat: String,
//        @Part("lon") lon: String,
//        @Part("categories") categories: String,
//        @Field("description") description: String
//    ): Call<RequestBody?>?


}
