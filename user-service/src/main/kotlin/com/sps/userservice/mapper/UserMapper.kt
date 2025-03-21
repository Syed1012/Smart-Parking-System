package com.sps.userservice.mapper

import com.sps.userservice.dto.UserRequestDto
import com.sps.userservice.dto.UserResponseDto
import com.sps.userservice.entity.User
import com.sps.userservice.entity.Role
import java.time.format.DateTimeFormatter

object UserMapper {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // Convert UserRequestDto to User Entity
    fun toEntity(dto: UserRequestDto): User {
        return User(
            username = dto.username,
            email = dto.email,
            password = dto.password,
            firstName = dto.firstName,
            lastName = dto.lastName,
            phoneNumber = dto.phoneNumber,
            role = Role.USER.value // Default role
        )
    }

    // Convert User Entity to UserResponseDto
    fun toResponseDto(user: User): UserResponseDto {
        return UserResponseDto(
            id = user.id,
            username = user.username,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            phoneNumber = user.phoneNumber ?: "",
            createdAt = user.createdAt.format(formatter)
        )
    }
}
