package com.bhavsar.vishal.moneytrackerapp.data.repository

import com.bhavsar.vishal.moneytrackerapp.data.network.AuthApi
import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.LoginRequest

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {
    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(LoginRequest(username, password))
    }
}