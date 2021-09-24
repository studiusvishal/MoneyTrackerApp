package com.bhavsar.vishal.moneytrackerapp.data.network

import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.LoginRequest
import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.SignUpRequest
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.LoginResponse
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("/api/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse
}