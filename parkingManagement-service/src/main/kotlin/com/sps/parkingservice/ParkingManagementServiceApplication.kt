package com.sps.parkingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkingManagementServiceApplication

fun main(args: Array<String>) {
    runApplication<ParkingManagementServiceApplication>(*args)
}