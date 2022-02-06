package com.example.webunihw_eatingplaces.ui.places

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.webunihw_eatingplaces.model.places.Place
import com.example.webunihw_eatingplaces.repository.places.PlaceDetailsRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val placeDetailsRepository: PlaceDetailsRepository = PlaceDetailsRepository()
    private val result = MutableLiveData<PlaceDetailsViewState>()

    fun getPlaceDetailsLiveData() = result

    fun getPlaceDetails(placeId: String) {

        result.value = DownloadPlaceDetailsInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = placeDetailsRepository.getPlaceDetails(placeId)
            when (response) {
                is NetworkResult -> {
                    val placeDetailsResult = response.result as Place

                    result.postValue(PlaceDetailsResponseSuccess(placeDetailsResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(PlaceDetailsResponseError(response.errorMessage.message!!))
                }
            }
        }
    }





}