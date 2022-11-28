package com.beproud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppAuthApplication

fun main(args: Array<String>) {
    runApplication<AppAuthApplication>(*args)
}
