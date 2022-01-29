package com.example.webunihw_eatingplaces.ui.auth

import com.example.webunihw_eatingplaces.model.auth.LoginResult
import okhttp3.ResponseBody

sealed class RegistrationViewState

//1: egy singleton, aminek 1 db értéke van
object RegistrationInProgress : RegistrationViewState()

//2: ha az állapot LoginResponseSuccess, akkor elkérem a LoginResult data-ját
data class RegistrationResponseSuccess(
    val data: ResponseBody
) : RegistrationViewState()

//3: ha az állapot LoginResponseError, akkor elkérem az error message-t
data class RegistrationResponseError(
    val exceptionMsg: String
) : RegistrationViewState()