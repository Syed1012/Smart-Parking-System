package com.sps.parkingservice.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class ParkingEventProducer(private val rabbitTemplate: RabbitTemplate) {

    private val logger = LoggerFactory.getLogger(ParkingEventProducer::class.java)

    fun sendParkingEvent(message: String, routingKey: String) {
        logger.info("Sending message: $message with routingKey: $routingKey")
        rabbitTemplate.convertAndSend("parking.direct.exchange", routingKey, message)
    }

    fun sendFanoutEvent(message: String) {
        logger.info("Broadcasting message to all services: $message")
        rabbitTemplate.convertAndSend("parking.fanout.exchange", "", message)
    }
    // Note: Routing key is empty ("") for fanout exchange.

    fun sendTopicEvent(message: String, routingKey: String) {
        logger.info("Sending Topic message: $message with routingKey: $routingKey")
        rabbitTemplate.convertAndSend("parking.topic.exchange", routingKey, message)
    }

}