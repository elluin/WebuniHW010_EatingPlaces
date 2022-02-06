package com.example.webunihw_eatingplaces.datasource

import com.example.webunihw_eatingplaces.network.RetrofitClient
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResponse
import com.example.webunihw_eatingplaces.utils.NetworkResult

object PlaceDetailDataSource {
    suspend fun getPlaceDetail(placeId: String): NetworkResponse<Any> {
        try {
            val response = RetrofitClient.placesApiInterface.getPlaceDetail(placeId)
            response?.let {
                return NetworkResult(it.body()!!)
            }

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }
}