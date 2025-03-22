package com.sps.parkingservice.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EnvConfig {

    @Bean
    fun loadEnvironmentVariables(): Boolean {
        val dotenv = Dotenv.configure()
            .filename(".env")
            .ignoreIfMissing()
            .load()

        dotenv.entries().forEach { entry ->
            System.setProperty(entry.key, entry.value)
        }

        return true
    }
}