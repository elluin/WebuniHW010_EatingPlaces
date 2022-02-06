package com.example.webunihw_eatingplaces.datasource

import android.util.Log
import com.example.webunihw_eatingplaces.network.RetrofitClient
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResponse
import com.example.webunihw_eatingplaces.utils.NetworkResult

object UploadPlaceDataSource {


suspend fun uploadPlace(filepath: String, fullName: String, postalCode: String, city: String, address: String, lat: String, lon: String, categories: String, desription: String): NetworkResponse<Any> {
    try {
        val response = RetrofitClient.placesApiInterface.uploadPlace(filepath, fullName, postalCode, city,address,lat, lon, categories,  desription)
        response?.let {
            return NetworkResult(it)
        }

    } catch (e: Exception) {
        return NetworkErrorResult(e)
    }


}


}