package com.sps.parkingservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class ParkingLog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val licensePlate: String,

    @Column(nullable = false)
    val entryTime: LocalDateTime = LocalDateTime.now(),

    @Column
    var exitTime: LocalDateTime? = null,

    @Column(nullable = false)
    var status: String = "IN" // Values: IN, OUT
) {
    constructor() : this(licensePlate = "")
}
