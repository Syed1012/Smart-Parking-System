package com.sps.parkingservice.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    // Queue Declaration
    @Bean
    fun queue(): Queue {
        return Queue("parkingQueue", true)
    }

    // Exchange Declaration
    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("parkingExchange")
    }

    // Binding Declaration (Queue to Exchange with Routing key)
    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("parkingRoutingKey")
    }

}


//Queue: parkingQueue — Holds the messages.

//Exchange: parkingExchange — Routes messages to queues using routing keys.

//Routing Key: parkingRoutingKey — Helps in identifying the queue.