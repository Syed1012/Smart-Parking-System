package com.sps.parkingservice.listener

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class TopicEventListener {

    private val logger = LoggerFactory.getLogger(TopicEventListener::class.java)

    @RabbitListener(queues = ["carQueue"])
    fun handleCarMessage(message: String) {
        logger.info("Car Queue Received: $message")
    }

    @RabbitListener(queues = ["bikeQueue"])
    fun handleBikeMessage(message: String) {
        logger.info("Bike Queue Received: $message")
    }

    @RabbitListener(queues = ["parkingGeneralQueue"])
    fun handleGeneralMessage(message: String) {
        logger.info("General Queue Received: $message")
    }
}