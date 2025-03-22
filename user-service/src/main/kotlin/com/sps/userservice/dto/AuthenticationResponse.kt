package com.sps.userservice.dto

data class AuthenticationResponse(
    val token: String,
    val message: String = "Authentication successful!"
)
