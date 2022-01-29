package com.example.webunihw_eatingplaces.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.repository.LoginRepository
import com.example.webunihw_eatingplaces.repository.RegistrationRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class RegistrationViewModel : ViewModel() {
    private var registrationRepository: RegistrationRepository = RegistrationRepository()

    private val result = MutableLiveData<RegistrationViewState>()

    fun getRegistrationLiveData() = result

    fun registration(email: String, username: String, password: String) {

        result.value = RegistrationInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = registrationRepository.getRegistrationDetails(email, username, password)
            when (response) {
                is NetworkResult -> {
                    val registrationResult = response.result as ResponseBody
                    result.postValue(RegistrationResponseSuccess(registrationResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(RegistrationResponseError(response.errorMessage.message!!))
                }
            }
        }
    }
}