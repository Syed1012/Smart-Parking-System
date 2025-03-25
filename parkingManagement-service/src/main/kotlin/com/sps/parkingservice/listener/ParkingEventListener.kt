package com.sps.parkingservice.listener

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ParkingEventListener {

    private val logger = LoggerFactory.getLogger(ParkingEventListener::class.java)

    @RabbitListener(queues = ["parkingEntryQueue"])
    fun handleEntryMessage(message: String) {
        logger.info("Received Entry Event: $message")
    }

    @RabbitListener(queues = ["parkingExitQueue"])
    fun handleExitMessage(message: String) {
        logger.info("Received Exit Event: $message")
    }

}