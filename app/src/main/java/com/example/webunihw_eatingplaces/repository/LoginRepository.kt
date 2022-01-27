package com.example.webunihw_eatingplaces.repository

import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class LoginRepository {
     suspend fun getLoginDetails(email: String, password: String) : NetworkResponse<Any> {
        return LoginDataSource.getUserLogin(email, password)
    }
}