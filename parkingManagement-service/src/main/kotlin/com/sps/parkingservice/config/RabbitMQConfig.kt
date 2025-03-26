package com.sps.parkingservice.config

import org.springframework.amqp.core.*
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    //  Direct Exchange
    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange("parking.direct.exchange")
    }

    // Parking Entry Queue
    @Bean
    fun parkingEntryQueue(): Queue {
        return Queue("parkingEntryQueue")
    }

    // Parking Exit Queue
    @Bean
    fun parkingExitQueue(): Queue {
        return Queue("parkingExitQueue")
    }

    // Binding for Entry
    @Bean
    fun parkingEntryBinding(): Binding {
        return BindingBuilder.bind(parkingEntryQueue())
            .to(directExchange())
            .with("parking.entry")
    }

    // Binding for Exit
    @Bean
    fun parkingExitBinding(): Binding {
        return BindingBuilder.bind(parkingExitQueue()) // ✅ Correct binding
            .to(directExchange())
            .with("parking.exit")
    }


    // Fanout Exchange
    @Bean
    fun fanoutExchange(): FanoutExchange {
        return FanoutExchange("parking.fanout.exchange")
    }

    // Billing Queue
    @Bean
    fun billingQueue(): Queue {
        return Queue("billingQueue")
    }

    // Notification Queue
    @Bean
    fun notificationQueue(): Queue {
        return Queue("notificationQueue")
    }

    // Audit Queue
    @Bean
    fun auditQueue(): Queue {
        return Queue("auditQueue")
    }

    // Bindings for Fanout Exchange (Fanout doesn't require routing keys)

    // For triggering notification update in billing about any update happened
    @Bean
    fun billingBinding(): Binding {
        return BindingBuilder.bind(billingQueue()).to(fanoutExchange())
    }

    // For triggering notification about any update happened
    @Bean
    fun notificationBinding(): Binding {
        return BindingBuilder.bind(notificationQueue()).to(fanoutExchange())
    }

    // For triggering notification in audit about any update happened
    @Bean
    fun auditBinding(): Binding {
        return BindingBuilder.bind(auditQueue()).to(fanoutExchange())
    }

    // Direct Exchange - Parking Queue Declaration
    @Bean
    fun queue(): Queue {
        return Queue("parkingQueue", true)
    }

    // Exchange parking Queue Declaration
    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("parkingExchange")
    }

    // Binding of parking queue Declaration (Queue to Exchange with Routing key)
    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("parkingRoutingKey")
    }

    // Topic Exchange
    @Bean
    fun topicExchange(): TopicExchange {
        return TopicExchange("parking.topic.exchange")
    }

    // Parking Payment Queue (Listens to payment.*)
    @Bean
    fun parkingPaymentQueue(): Queue {
        return Queue("parkingPaymentQueue")
    }

    // Parking Notification Queue (Listens to notification.#)
    @Bean
    fun parkingNotificationQueue(): Queue {
        return Queue("parkingNotificationQueue")
    }

    // Parking General Queue (Listens to parking.*)
    @Bean
    fun parkingGeneralQueue(): Queue {
        return Queue("parkingGeneralQueue")
    }

    // Bindings for Topic Exchange
    @Bean
    fun parkingPaymentBinding(): Binding {
        return BindingBuilder.bind(parkingPaymentQueue()).to(topicExchange()).with("payment.*")
    }

    @Bean
    fun parkingNotificationBinding(): Binding {
        return BindingBuilder.bind(parkingNotificationQueue()).to(topicExchange()).with("notification.#")
    }

    @Bean
    fun parkingGeneralBinding(): Binding {
        return BindingBuilder.bind(parkingGeneralQueue()).to(topicExchange()).with("parking.*")
    }

}


//Queue: parkingQueue — Holds the messages.

//Exchange: parkingExchange — Routes messages to queues using routing keys.

//Routing Key: parkingRoutingKey — Helps in identifying the queue.