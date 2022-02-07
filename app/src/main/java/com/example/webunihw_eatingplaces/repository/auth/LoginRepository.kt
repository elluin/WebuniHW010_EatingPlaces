package com.example.webunihw_eatingplaces.repository.auth

import androidx.lifecycle.LiveData
import com.example.webunihw_eatingplaces.database.UserDAO
import com.example.webunihw_eatingplaces.datasource.LoginDataSource
import com.example.webunihw_eatingplaces.model.db.User
import com.example.webunihw_eatingplaces.utils.NetworkResponse

class LoginRepository(private val userDAO: UserDAO) {

     suspend fun getLoginDetails(email: String, password: String) : NetworkResponse<Any> {
        return LoginDataSource.getUserLogin(email, password)
    }

    fun getAllUsers() : LiveData<List<User>> {
        return userDAO.getUsers()
    }

    suspend fun insert(user: User) {
        userDAO.insertUser(user)
    }

    suspend fun delete() {
        userDAO.deleteAllUser()
    }
}