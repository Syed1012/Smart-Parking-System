package com.sps.userservice.service.impl

import com.sps.userservice.dto.UserRequestDto
import com.sps.userservice.entity.User
import com.sps.userservice.mapper.UserMapper
import com.sps.userservice.repository.UserRepository
import com.sps.userservice.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(userRequestDto: UserRequestDto): User {
        // Check if user already exists by email or username
        if (userRepository.findByEmail(userRequestDto.email) != null) {
            throw IllegalArgumentException("User with this email already exists.")
        }
        if (userRepository.findByUsername(userRequestDto.username) != null) {
            throw IllegalArgumentException("User with this username already exists.")
        }

        // Map DTO to Entity
        val user = UserMapper.toEntity(userRequestDto)

        // Save user
        return userRepository.save(user)
    }

    override fun getUserById(id: Int): User {
        return userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with ID: $id") }
    }
}
