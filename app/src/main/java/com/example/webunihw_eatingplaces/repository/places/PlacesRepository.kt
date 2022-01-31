package com.example.webunihw_eatingplaces.repository.places

import com.example.webunihw_eatingplaces.database.UserDAO
import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.datasource.PlacesDataSource
import com.example.webunihw_eatingplaces.model.db.User
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class PlacesRepository {

    suspend fun getPlaceList() : NetworkResponse<Any> {
        return PlacesDataSource.getPlaces()
    }


}