package com.example.webunihw_eatingplaces.ui.places.upload

import okhttp3.ResponseBody

sealed class UploadPlaceViewState

object UploadInProgress : UploadPlaceViewState()

data class UploadPlaceResponseSuccess(
    val data: ResponseBody
) : UploadPlaceViewState()

data class UploadPlaceResponseError(
    val exceptionMsg: String
) : UploadPlaceViewState()