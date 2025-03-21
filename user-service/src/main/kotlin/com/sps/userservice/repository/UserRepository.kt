package com.sps.userservice.repository

import com.sps.userservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
}