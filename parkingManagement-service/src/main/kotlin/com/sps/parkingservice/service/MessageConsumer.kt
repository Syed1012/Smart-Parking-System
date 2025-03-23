package com.sps.parkingservice.service

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class MessageConsumer {

    @RabbitListener(queues = ["parkingQueue"])
    fun receiveMessage(message: String) {
        println("Received message: $message")
    }
}

//The @RabbitListener annotation listens to messages from parkingQueue.