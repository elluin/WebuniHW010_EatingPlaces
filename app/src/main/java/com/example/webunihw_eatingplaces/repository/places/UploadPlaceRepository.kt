package com.example.webunihw_eatingplaces.repository.places

import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.datasource.RegistrationDataSource
import com.example.webunihw_eatingplaces.datasource.UploadPlaceDataSource
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class UploadPlaceRepository {
    suspend fun uploadPlace(image: String, fullName: String, postalCode: String, city: String, address: String, lat: Double, lon: Double, categories: String, desription: String) : NetworkResponse<Any> {
        return UploadPlaceDataSource.uploadPlace(image, fullName, postalCode, city,address,lat, lon, categories, desription)
    }
}