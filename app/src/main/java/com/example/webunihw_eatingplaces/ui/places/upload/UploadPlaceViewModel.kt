package com.example.webunihw_eatingplaces.ui.places.upload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webunihw_eatingplaces.repository.places.UploadPlaceRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class UploadPlaceViewModel : ViewModel() {
    private var uploadPlaceRepository: UploadPlaceRepository = UploadPlaceRepository()

    private val result = MutableLiveData<UploadPlaceViewState>()

    fun getUploadPlaceLiveData() = result

    fun upload(fullName: String, postalCode: String, city: String, address: String, lat: String, lon: String, categories: String, image: String, desription: String) {

        result.value = UploadInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = uploadPlaceRepository.uploadPlace(fullName, postalCode, city,address,lat, lon, categories, image, desription)
            when (response) {
                is NetworkResult -> {
                    val uploadResult = response.result as ResponseBody
                    result.postValue(UploadPlaceResponseSuccess(uploadResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(UploadPlaceResponseError(response.errorMessage.message!!))
                }
            }
        }
    }

}