package com.example.webunihw_eatingplaces.datasource

import com.example.webunihw_eatingplaces.network.RetrofitClient
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResponse
import com.example.webunihw_eatingplaces.utils.NetworkResult

object PlacesDataSource {
    //egy PlaceListResult típusú live datát ad vissza
    suspend fun getPlaces(): NetworkResponse<Any> {
        try {
            val response = RetrofitClient.placesApiInterface.getPlaces()
            response?.let {
                return NetworkResult(it.body()!!)
            }

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }


}