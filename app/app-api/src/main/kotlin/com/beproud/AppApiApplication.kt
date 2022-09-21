package com.beproud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.beproud"])
@SpringBootApplication
class AppApiApplication

fun main(args: Array<String>) {
    runApplication<AppApiApplication>(*args)
}
