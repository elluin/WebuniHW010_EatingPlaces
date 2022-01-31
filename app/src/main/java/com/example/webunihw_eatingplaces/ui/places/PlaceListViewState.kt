package com.example.webunihw_eatingplaces.ui.places

import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.model.auth.LoginResult

sealed class PlaceListViewState

//1: egy singleton, aminek 1 db értéke van
object DownloadPlacesInProgress : PlaceListViewState()

//2: ha az állapot PlacesResponseSuccess, akkor elkérem a PlaceListResult data-ját
data class PlaceListResponseSuccess(
    val data: PlaceListResult
) : PlaceListViewState()

//3: ha az állapot LoginResponseError, akkor elkérem az error message-t
data class PlaceListResponseError(
    val exceptionMsg: String
) : PlaceListViewState()