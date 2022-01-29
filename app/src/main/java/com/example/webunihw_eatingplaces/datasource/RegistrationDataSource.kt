package com.example.webunihw_eatingplaces.datasource

import android.util.Log
import com.example.webunihw_eatingplaces.network.RetrofitClient
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResponse
import com.example.webunihw_eatingplaces.utils.NetworkResult

object RegistrationDataSource {


suspend fun getRegistration(email: String, username: String, password: String): NetworkResponse<Any> {
    try {
        val response = RetrofitClient.authApiInterface.registration(email, username, password)
        response?.let {
            return NetworkResult(it)
        }

    } catch (e: Exception) {
        return NetworkErrorResult(e)
    }


}


}