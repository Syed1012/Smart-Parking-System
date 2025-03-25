package com.sps.parkingservice.config

import org.springframework.amqp.core.*
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

    //  Notification Queue
    @Bean
    fun notificationQueue(): Queue {
        return Queue("notificationQueue")
    }

    // Audit Queue
    @Bean
    fun auditQueue(): Queue {
        return Queue("auditQueue")
    }

    // Bindings (Fanout doesn't require routing keys)

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

    // Parking Queue Declaration
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

}


//Queue: parkingQueue — Holds the messages.

//Exchange: parkingExchange — Routes messages to queues using routing keys.

//Routing Key: parkingRoutingKey — Helps in identifying the queue.