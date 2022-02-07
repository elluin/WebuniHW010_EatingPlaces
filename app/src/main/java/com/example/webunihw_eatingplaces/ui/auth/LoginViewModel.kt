package com.example.webunihw_eatingplaces.ui.auth

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.webunihw_eatingplaces.database.AppDatabase
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.model.db.User
import com.example.webunihw_eatingplaces.repository.auth.LoginRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application)  : AndroidViewModel(application)  {

    private var loginRepository: LoginRepository
    val allUsers: LiveData<List<User>>
    private val result = MutableLiveData<LoginViewState>()

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        loginRepository = LoginRepository(userDao)
        allUsers = loginRepository.getAllUsers()
    }

    fun getLoginLiveData() = result


    fun login(email: String, password: String) {

        result.value = LoginInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = loginRepository.getLoginDetails(email, password)
            when (response) {
                is NetworkResult -> {
                    val loginResult = response.result as LoginResult

                    deleteUsers()

                    result.postValue(LoginResponseSuccess(loginResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(LoginResponseError(response.errorMessage.message!!))
                }
            }
        }
    }


    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO) {
        loginRepository.insert(user)
    }



    fun deleteUsers() = viewModelScope.launch(Dispatchers.IO) {
        loginRepository.delete()
    }
}