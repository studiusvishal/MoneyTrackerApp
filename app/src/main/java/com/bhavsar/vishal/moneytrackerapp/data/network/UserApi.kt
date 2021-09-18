package com.bhavsar.vishal.moneytrackerapp.data.network

import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {
    @GET("user")
    suspend fun getUser(): LoginResponse
}
