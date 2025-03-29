package com.sps.parkingservice.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.handler.annotation.Headers
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

    fun sendHeaderEvent(message: String, headers: Map<String, Any>) {
        logger.info("Sending message to header exchange: $message with headers: $headers")
        rabbitTemplate.convertAndSend("parking.header.exchange", "", message) { msg ->
            headers.forEach { (key, value) ->
                msg.messageProperties.headers[key] = value
            }
            msg
        }
    }

}