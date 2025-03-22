package com.sps.userservice.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class LogoutService {
    private val tokenBlacklist = ConcurrentHashMap<String, Boolean>()

    fun logout(token: String) {
        tokenBlacklist[token] = true
    }

    fun isTokenBlacklisted(token: String): Boolean {
        return tokenBlacklist.containsKey(token)
    }
}