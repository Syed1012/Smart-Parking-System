package com.sps.parkingservice.listener

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class HeaderEventListener {

    private val logger = LoggerFactory.getLogger(HeaderEventListener::class.java)

    @RabbitListener(queues = ["carHeaderQueue"])
    fun handleCarMessage(message: String) {
        logger.info("Car Header Queue Received: $message")
    }

    @RabbitListener(queues = ["bikeHeaderQueue"])
    fun handleBikeMessage(message: String) {
        logger.info("Bike Header Queue Received: $message")
    }

    @RabbitListener(queues = ["priorityQueue"])
    fun handlePriorityMessage(message: String) {
        logger.info("priority Queue Received: $message")
    }

}