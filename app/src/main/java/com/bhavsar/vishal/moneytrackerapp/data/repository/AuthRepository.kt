package com.bhavsar.vishal.moneytrackerapp.data.repository

import com.bhavsar.vishal.moneytrackerapp.data.UserPreferences
import com.bhavsar.vishal.moneytrackerapp.data.network.AuthApi
import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.LoginRequest
import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.SignUpRequest
import java.util.*

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {
    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(LoginRequest(username, password))
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun signUp(fullName: String, username: String, email: String, password: String) =
        safeApiCall {
            api.signUp(SignUpRequest(
                fullName,
                username,
                email,
                password,
                listOf("user")
            ))
        }
}