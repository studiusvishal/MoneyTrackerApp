package com.bhavsar.vishal.moneytrackerapp.data.network

import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("/api/user/getCurrentUser")
    suspend fun getUser(): LoginResponse

    @POST("/api/user/logout")
    suspend fun logout(): ResponseBody
}
