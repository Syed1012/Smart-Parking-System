package com.sps.parkingservice.service

import com.sps.parkingservice.entity.ParkingLog
import com.sps.parkingservice.repository.ParkingLogRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ParkingLogService(private val parkingLogRepository: ParkingLogRepository) {

    private val logger = LoggerFactory.getLogger(ParkingLogService::class.java)

    fun validateEntry(licensePlate: String): Boolean {
        val existingLog = parkingLogRepository.findByLicensePlateAndStatus(licensePlate, "IN")
        return existingLog == null
    }

    fun registerEntry(licensePlate: String): ParkingLog {
        if (!validateEntry(licensePlate)) {
            throw IllegalStateException("Vehicle with license plate $licensePlate is already parked")
        }

        val parkingLog = ParkingLog(licensePlate = licensePlate)
        return parkingLogRepository.save(parkingLog)
    }

    fun registerExit(licensePlate: String) {
        val parkingLog = parkingLogRepository.findByLicensePlateAndStatus(licensePlate, "IN")
            ?: throw IllegalStateException("No active parking record found for license plate $licensePlate")

        parkingLog.exitTime = LocalDateTime.now()
        parkingLog.status = "OUT"
        parkingLogRepository.save(parkingLog)
    }
}