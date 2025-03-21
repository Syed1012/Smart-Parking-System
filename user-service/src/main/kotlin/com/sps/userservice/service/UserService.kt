package com.sps.userservice.service

import com.sps.userservice.dto.UserRequestDto
import com.sps.userservice.entity.User

interface UserService {
    fun createUser(userRequestDto: UserRequestDto): User
    fun getUserById(id: Int): User
}
