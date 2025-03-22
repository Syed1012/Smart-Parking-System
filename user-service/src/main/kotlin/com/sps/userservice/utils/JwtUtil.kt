package com.sps.userservice.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @Value("\${jwt.expiration}")
    private lateinit var expirationTime: String

    private val key: SecretKey by lazy {
        Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
    }

    // ✅ Create parser once and reuse
    private val jwtParser: JwtParser by lazy {
        Jwts.parser().verifyWith(key).build() // Use verifyWith instead of setSigningKey
    }

    // ✅ Generate JWT Token
    fun generateToken(username: String, role: String): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims["role"] = role

        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith(key)
            .compact()
    }

    // ✅ Extract Username from JWT Token
    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    // ✅ Extract Role from JWT Token
    fun extractRole(token: String): String {
        return extractAllClaims(token)["role"] as String
    }

    // ✅ Validate JWT Token
    fun validateToken(token: String, username: String): Boolean {
        return try {
            val extractedUsername = extractUsername(token)
            !isTokenExpired(token) && extractedUsername == username
        } catch (e: Exception) {
            println("Invalid token: ${e.message}")
            false
        }
    }

    // ✅ Check Token Expiration
    fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }

    // ✅ Extract all claims using latest API
    private fun extractAllClaims(token: String): Claims {
        return jwtParser.parseSignedClaims(token).payload
    }
}
