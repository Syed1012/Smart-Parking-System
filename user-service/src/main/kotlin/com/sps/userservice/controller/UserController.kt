package com.sps.userservice.controller

import com.sps.userservice.dto.UserRequestDto
import com.sps.userservice.dto.UserResponseDto
import com.sps.userservice.mapper.UserMapper
import com.sps.userservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userRequestDto: UserRequestDto): ResponseEntity<UserResponseDto> {
        val user = userService.createUser(userRequestDto)
        return ResponseEntity.ok(UserMapper.toResponseDto(user))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<UserResponseDto> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(UserMapper.toResponseDto(user))
    }
}
