package com.example.webunihw_eatingplaces.repository

import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.datasource.RegistrationDataSource
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class RegistrationRepository {
    suspend fun getRegistrationDetails(username: String, email: String, password: String) : NetworkResponse<Any> {
        return RegistrationDataSource.getRegistration(email, username, password)
    }
}