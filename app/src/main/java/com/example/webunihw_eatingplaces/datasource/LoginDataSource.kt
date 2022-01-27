package com.example.webunihw_eatingplaces.datasource


import com.example.webunihw_eatingplaces.network.RetrofitClient
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResponse
import com.example.webunihw_eatingplaces.utils.NetworkResult

object LoginDataSource {


    //egy LoginResult típusú live datát ad vissza
    suspend fun getUserLogin(email: String, password: String): NetworkResponse<Any> {
        try {
            val response = RetrofitClient.apiInterface.login(email, password)
            response?.let {
                return NetworkResult(it.body()!!)
            }

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }


    }


}