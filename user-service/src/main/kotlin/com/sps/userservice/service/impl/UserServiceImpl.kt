package com.sps.userservice.service.impl

import com.sps.userservice.dto.UserRequestDto
import com.sps.userservice.entity.User
import com.sps.userservice.mapper.UserMapper
import com.sps.userservice.repository.UserRepository
import com.sps.userservice.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun createUser(userRequestDto: UserRequestDto): User {
        // Check if user already exists by email or username
        if (userRepository.findByEmail(userRequestDto.email) != null) {
            throw IllegalArgumentException("User with this email already exists.")
        }
        if (userRepository.findByUsername(userRequestDto.username) != null) {
            throw IllegalArgumentException("User with this username already exists.")
        }

        //Encrypt password
        val encryptedPassword = passwordEncoder.encode(userRequestDto.password)

        // Map DTO to Entity and setting encrypted password.
        val user = UserMapper.toEntity(userRequestDto).copy(password = encryptedPassword)

        // Save user
        return userRepository.save(user)
    }

    override fun getUserById(id: Int): User {
        return userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with ID: $id") }
    }
}
