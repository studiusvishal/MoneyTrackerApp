package com.bhavsar.vishal.moneytrackerapp.data.repository

import com.bhavsar.vishal.moneytrackerapp.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {
    suspend fun getUser() = safeApiCall { api.getUser() }
}