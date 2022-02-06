package com.example.webunihw_eatingplaces.ui.places

import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.model.places.Place

sealed class PlaceDetailsViewState

//1: egy singleton, aminek 1 db értéke van
object DownloadPlaceDetailsInProgress : PlaceDetailsViewState()

//2: ha az állapot PlacesResponseSuccess, akkor elkérem a PlaceListResult data-ját
data class PlaceDetailsResponseSuccess(
    val data: Place
) : PlaceDetailsViewState()

//3: ha az állapot LoginResponseError, akkor elkérem az error message-t
data class PlaceDetailsResponseError(
    val exceptionMsg: String
) : PlaceDetailsViewState()