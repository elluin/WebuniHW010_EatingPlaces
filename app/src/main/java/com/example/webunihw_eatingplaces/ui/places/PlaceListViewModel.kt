package com.example.webunihw_eatingplaces.ui.places

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.repository.places.PlacesRepository
import com.example.webunihw_eatingplaces.utils.NetworkErrorResult
import com.example.webunihw_eatingplaces.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceListViewModel(application: Application) : AndroidViewModel(application) {

    private val placesRepository: PlacesRepository = PlacesRepository()
    private val result = MutableLiveData<PlaceListViewState>()

    fun getPlacesLiveData() = result

    fun getPlaces() {

        result.value = DownloadPlacesInProgress

        viewModelScope.launch(Dispatchers.IO) {
            val response = placesRepository.getPlaceList()
            when (response) {
                is NetworkResult -> {
                    val placesResult = response.result as PlaceListResult

                    result.postValue(PlaceListResponseSuccess(placesResult))
                }
                is NetworkErrorResult -> {
                    result.postValue(PlaceListResponseError(response.errorMessage.message!!))
                }
            }
        }
    }





}