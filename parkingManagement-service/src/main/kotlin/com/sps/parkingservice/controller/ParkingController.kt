package com.sps.parkingservice.controller

import com.sps.parkingservice.dto.ParkingRequestDTO
import com.sps.parkingservice.entity.ParkingLog
import com.sps.parkingservice.producer.ParkingEventProducer
import com.sps.parkingservice.service.ParkingLogService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/parking")
class ParkingController(
    private val parkingEventProducer: ParkingEventProducer,
    private val parkingLogService: ParkingLogService
) {

    @PostMapping("/entry")
    fun handleEntryEvent(@RequestBody request: ParkingRequestDTO): String {
        val licensePlate = request.licensePlate
        return try {
            val parkingLog = parkingLogService.registerEntry(licensePlate)
            parkingEventProducer.sendParkingEvent("Vehicle entered: $licensePlate", "parking.entry")
            "Entry event registered successfully. Log ID: ${parkingLog.id}"
        } catch (e: IllegalStateException) {
            "Error: ${e.message}"
        }
    }

    @PostMapping("/exit")
    fun handleExitEvent(@RequestBody request: ParkingRequestDTO): String {
        val licensePlate = request.licensePlate
        return try {
            parkingLogService.registerExit(licensePlate)
            parkingEventProducer.sendParkingEvent("Vehicle exited: $licensePlate", "parking.exit")
            "Exit event registered successfully for vehicle with license plate: $licensePlate"
        } catch (e: IllegalStateException) {
            "Error: ${e.message}"
        }
    }

    @PostMapping("/broadcast")
    fun broadcastEvent(@RequestBody message: String): String {
        parkingEventProducer.sendFanoutEvent(message)
        return "Message broadcast to all services."
    }

    @PostMapping("/topic")
    fun sendTopicEvent(@RequestBody message: String, @RequestParam routingKey: String): String {
        parkingEventProducer.sendTopicEvent(message, routingKey)
        return "Topic message send with routingKey: $routingKey"
    }

    @PostMapping("/header")
    fun sendHeaderEvent(@RequestBody message: String, @RequestParam headers: Map<String, String>): String {
        parkingEventProducer.sendHeaderEvent(message, headers)
        return "Header message sent successfully with headers: $headers"
    }
}
