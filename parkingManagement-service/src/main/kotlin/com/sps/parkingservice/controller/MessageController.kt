package com.sps.parkingservice.controller

import com.sps.parkingservice.service.MessageProducer
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/parking")
class MessageController(private val messageProducer: MessageProducer) {

    @PostMapping("/send")
    fun sendMessage(@RequestBody message: String): String {
        messageProducer.sendMessage(message)
        return "Message sent: $message"
    }
}
