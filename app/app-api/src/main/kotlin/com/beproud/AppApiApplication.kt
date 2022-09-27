package com.beproud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableCaching
@EnableJpaRepositories(basePackages = ["com.beproud"])
@SpringBootApplication
class AppApiApplication

fun main(args: Array<String>) {
    runApplication<AppApiApplication>(*args)
}