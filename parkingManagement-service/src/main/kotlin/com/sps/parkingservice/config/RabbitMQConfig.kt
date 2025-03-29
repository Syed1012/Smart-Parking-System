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

    // Bike Queue (Listens to bike.*)
    @Bean
    fun bikeQueue(): Queue {
        return Queue("bikeQueue")
    }

    // Car Queue (Listens to car.*)
    @Bean
    fun carQueue(): Queue {
        return Queue("carQueue")
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
    fun bikeBinding(): Binding {
        return BindingBuilder.bind(bikeQueue()).to(topicExchange()).with("bike.*")
    }

    @Bean
    fun carBinding(): Binding {
        return BindingBuilder.bind(carQueue()).to(topicExchange()).with("car.*")
    }

    @Bean
    fun parkingGeneralBinding(): Binding {
        return BindingBuilder.bind(parkingGeneralQueue()).to(topicExchange()).with("parking.*")
    }


    // Header Exchange
    @Bean
    fun headerExchange(): HeadersExchange {
        return HeadersExchange("parking.header.exchange")
    }

    // Defining Queues
    @Bean
    fun carHeaderQueue(): Queue{
        return Queue("carHeaderQueue")
    }

    @Bean
    fun bikeHeaderQueue(): Queue{
        return Queue("bikeHeaderQueue")
    }

    @Bean
    fun priorityQueue(): Queue{
        return Queue("priorityQueue")
    }

    // Binding Queues to Header Exchange
    @Bean
    fun carHeaderBinding(): Binding{
        return BindingBuilder.bind(carHeaderQueue()).to(headerExchange()).where("vehicleType").matches("car")
    }

    @Bean
    fun bikeHeaderBinding(): Binding{
        return BindingBuilder.bind(bikeHeaderQueue()).to(headerExchange()).where("vehicleType").matches("bike")
    }

    @Bean
    fun priorityBinding(): Binding {
        return BindingBuilder.bind(priorityQueue())
            .to(headerExchange())
            .whereAll(mapOf("vehicleType" to "car", "priority" to "high"))
            .match()
    }

}


//Queue: parkingQueue — Holds the messages.

//Exchange: parkingExchange — Routes messages to queues using routing keys.

//Routing Key: parkingRoutingKey — Helps in identifying the queue.