package com.bhavsar.vishal.moneytrackerapp.data.payload.requests

data class SignUpRequest(
    val fullName: String,
    val username: String,
    val email: String,
    val password: String,
    val role: List<String>
)
