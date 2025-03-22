package com.sps.userservice.dto

data class AuthenticationRequest(
    val usernameOrEmail: String,
    val password: String
)
