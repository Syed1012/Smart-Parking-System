package com.sps.userservice.dto

data class UserRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String?,
    val phoneNumber: String
)

data class UserResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String?,
    val phoneNumber: String,
    val createdAt: String,
)