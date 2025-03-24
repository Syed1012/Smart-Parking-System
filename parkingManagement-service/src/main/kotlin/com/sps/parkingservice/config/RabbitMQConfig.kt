package com.sps.parkingservice.config

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    // Exchange
    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange("parking.direct.exchange")
    }

    // Parking Entry Queue
    @Bean
    fun parkingEntryQueue(): Queue{
        return Queue("parkingEntryQueue")
    }

    // Binding for Entry
    @Bean
    fun parkingEntryBinding(): Binding{
        return BindingBuilder.bind(parkingEntryQueue())
            .to(directExchange())
            .with("parking.entry")
    }

    // Binding for Exit
    @Bean
    fun parkingExitBinding(): Binding{
        return BindingBuilder.bind(parkingEntryQueue())
            .to(directExchange())
            .with("parking.exit")
    }

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