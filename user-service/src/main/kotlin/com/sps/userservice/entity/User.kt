package com.sps.userservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val firstName: String,

    val lastName: String? = null,

    @Column(nullable = false, unique = true)
    val phoneNumber: String? = null,

    @Column(nullable = false)
    val role: Int = Role.USER.value, // Default Role as USER (2)

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime? = null
) {
    constructor() : this(0, "", "", "", "", null, null, Role.USER.value, LocalDateTime.now(), null)
}