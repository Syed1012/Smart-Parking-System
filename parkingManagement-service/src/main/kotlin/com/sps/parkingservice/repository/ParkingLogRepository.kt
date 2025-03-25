package com.sps.parkingservice.repository

import com.sps.parkingservice.entity.ParkingLog
import org.springframework.data.jpa.repository.JpaRepository

interface ParkingLogRepository : JpaRepository<ParkingLog, Long> {
    fun findByLicensePlateAndStatus(licensePlate: String, status: String): ParkingLog?
}