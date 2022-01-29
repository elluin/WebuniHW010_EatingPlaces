package com.example.webunihw_eatingplaces.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.repository.LoginRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var loginRepository: LoginRepository = LoginRepository()

    private val result = MutableLiveData<LoginViewState>()

    fun getLoginLiveData() = result

    fun login(email: String, password: String) {

        result.value = LoginInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = loginRepository.getLoginDetails(email, password)
            when (response) {
                is NetworkResult -> {
                    val loginResult = response.result as LoginResult

                    result.postValue(LoginResponseSuccess(loginResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(LoginResponseError(response.errorMessage.message!!))
                }
            }
        }
    }
}