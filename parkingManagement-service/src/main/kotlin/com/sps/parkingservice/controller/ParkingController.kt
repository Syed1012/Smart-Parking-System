package com.sps.parkingservice.controller

import com.sps.parkingservice.producer.ParkingEventProducer
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/parking")
class ParkingController(private val parkingEventProducer: ParkingEventProducer) {

    @PostMapping("/entry")
    fun sendEntryEvent(@RequestBody message: String): String {
        parkingEventProducer.sendParkingEvent(message, "parking.entry")
        return "Entry event sent successfully."
    }

    @PostMapping("/exit")
    fun sendExitEvent(@RequestBody message: String): String {
        parkingEventProducer.sendParkingEvent(message, "parking.exit")
        return "Exit event sent successfully!"
    }

    @PostMapping("/broadcast")
    fun broadcastEvent(@RequestBody message: String): String {
        parkingEventProducer.sendFanoutEvent(message)
        return "Message broadcast to all services"
    }
}