package com.sps.parkingservice.consumer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class ParkingMessageListener{

    private val logger = LoggerFactory.getLogger(ParkingMessageListener::class.java)

    @RabbitListener(queues = ["parkingQueue"], errorHandler = "customRabbitErrorHandler")
    fun receiveMessage(message: String){
        logger.info("Received message: $message")

        // Simulating error for testing
        if(message.contains("error")){
            throw RuntimeException("Simulated Error: Invalid message received.")
        }
    }

}