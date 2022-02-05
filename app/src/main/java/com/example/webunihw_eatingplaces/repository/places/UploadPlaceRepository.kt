package com.example.webunihw_eatingplaces.repository.places

import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.datasource.RegistrationDataSource
import com.example.webunihw_eatingplaces.datasource.UploadPlaceDataSource
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class UploadPlaceRepository {
    suspend fun uploadPlace(fullName: String, postalCode: String, city: String, address: String, lat: String, lon: String, categories: String, image: String, desription: String) : NetworkResponse<Any> {
        return UploadPlaceDataSource.uploadPlace(fullName, postalCode, city,address,lat, lon, categories, image, desription)
    }
}