package com.sps.userservice.entity

enum class Role(val value: Int) {
    ADMIN(0),
    MANAGER(1),
    USER(2);

    companion object {
        fun fromValue(value: Int): Role {
            return entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Invalid role value: $value")
        }
    }

}