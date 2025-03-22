package com.sps.userservice.service.impl

import com.sps.userservice.dto.AuthenticationRequest
import com.sps.userservice.dto.AuthenticationResponse
import com.sps.userservice.repository.UserRepository
import com.sps.userservice.service.AuthService
import com.sps.userservice.utils.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) : AuthService {

    override fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        //Check if user exists by username or email
        val user = userRepository.findByUsername(authRequest.usernameOrEmail)
            ?: userRepository.findByEmail(authRequest.usernameOrEmail)
            ?: throw IllegalArgumentException("User not found with username or email: ${authRequest.usernameOrEmail}")

        // Validating password
        if (!passwordEncoder.matches(authRequest.password, user.password)) {
            throw IllegalArgumentException("Invalid credentials")
        }

        // Authenticating using Spring Security
        val authToken = UsernamePasswordAuthenticationToken(user.username, authRequest.password)
        authenticationManager.authenticate(authToken)

        // Generate JWT token
        val token = jwtUtil.generateToken(user.username, user.role.toString())
        return AuthenticationResponse(token)
    }
}