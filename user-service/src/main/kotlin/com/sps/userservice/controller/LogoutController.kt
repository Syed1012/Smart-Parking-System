package com.sps.userservice.controller

import com.sps.userservice.service.LogoutService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/auth")
class LogoutController(private val logoutService: LogoutService) {

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token or token missing.")
        }

        val token = authHeader.substring(7)
        logoutService.logout(token)
        return ResponseEntity.ok("Successfully logged out. Token invalidated.")
    }
}