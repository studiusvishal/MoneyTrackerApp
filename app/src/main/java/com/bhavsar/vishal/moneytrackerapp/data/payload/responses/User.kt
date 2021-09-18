package com.bhavsar.vishal.moneytrackerapp.data.payload.responses

data class User(
    val email: String,
    val enabled: Boolean,
    val id: Int,
    val name: String,
    val roles: List<String>,
    val token: String?,
    val type: String,
    val username: String,
    val using2FA: Boolean
)
