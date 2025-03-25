package com.sps.parkingservice.listener

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class FanoutEventListener {

    private val logger = LoggerFactory.getLogger(FanoutEventListener::class.java)

    @RabbitListener(queues = ["billingQueue"])
    fun handleBillingEvent(message: String) {
        logger.info("Billing service received: $message")
    }

    @RabbitListener(queues = ["notificationQueue"])
    fun handleNotificationEvent(message: String) {
        logger.info("Notification service received: $message")
    }

    @RabbitListener(queues = ["auditQueue"])
    fun handleAuditEvent(message: String) {
        logger.info("Audit service received: $message")
    }
}