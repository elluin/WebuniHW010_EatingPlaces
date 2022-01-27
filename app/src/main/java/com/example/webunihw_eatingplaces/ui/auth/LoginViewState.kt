package com.example.webunihw_eatingplaces.ui.auth

import com.example.webunihw_eatingplaces.model.auth.LoginResult

sealed class LoginViewState

//1: egy singleton, aminek 1 db értéke van
object InProgress : LoginViewState()

//2: ha az állapot LoginResponseSuccess, akkor elkérem a LoginResult data-ját
data class LoginResponseSuccess(
    val data: LoginResult
) : LoginViewState()

//3: ha az állapot LoginResponseError, akkor elkérem az error message-t
data class LoginResponseError(
    val exceptionMsg: String
) : LoginViewState()