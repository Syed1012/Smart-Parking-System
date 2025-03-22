package com.sps.userservice.controller

import com.sps.userservice.dto.AuthenticationRequest
import com.sps.userservice.dto.AuthenticationResponse
import com.sps.userservice.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/auth")
class AuthenticationController(private val authService: AuthService) {

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        val authResponse = authService.authenticate(authRequest)
        return ResponseEntity.ok(authResponse)
    }
}