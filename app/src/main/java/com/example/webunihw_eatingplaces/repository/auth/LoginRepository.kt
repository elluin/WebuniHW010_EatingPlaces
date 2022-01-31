package com.example.webunihw_eatingplaces.repository.auth

import com.example.webunihw_eatingplaces.database.UserDAO
import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.model.db.User
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class LoginRepository {
    lateinit var userDAO: UserDAO
     suspend fun getLoginDetails(email: String, password: String) : NetworkResponse<Any> {
        return LoginDataSource.getUserLogin(email, password)
    }

    suspend fun insert(user: User) {
        userDAO. insertUser(user)
    }
}