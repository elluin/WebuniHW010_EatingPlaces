package com.example.webunihw_eatingplaces.repository.places

import com.example.webunihw_eatingplaces.datasource.PlaceDetailDataSource
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class PlaceDetailsRepository {
    suspend fun getPlaceDetails(placeId: String) : NetworkResponse<Any> {
        return PlaceDetailDataSource.getPlaceDetail(placeId)
    }
}