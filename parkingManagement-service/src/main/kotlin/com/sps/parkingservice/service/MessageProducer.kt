package com.sps.parkingservice.service

import org.aspectj.bridge.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class MessageProducer(private val rabbitTemplate: RabbitTemplate) {
    fun sendMessage(message: String) {
        println("Sending message: $message")
        rabbitTemplate.convertAndSend("parkingExchange", "parkingRoutingKey", message)
    }
}

//This service uses RabbitTemplate to send messages to the RabbitMQ exchange.