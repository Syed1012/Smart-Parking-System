package com.sps.userservice.service

import com.sps.userservice.dto.AuthenticationRequest
import com.sps.userservice.dto.AuthenticationResponse

interface AuthService {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse
}