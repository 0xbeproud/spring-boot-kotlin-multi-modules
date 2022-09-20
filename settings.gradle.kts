rootProject.name = "spring-boot-kotlin-multi-modules"

//pluginManagement {
//    val kotlinVersion: String by settings
//    val springBootVersion: String by settings
//    val springDependencyManagementVersion: String by settings
//    val palantirDockerVersion: String by settings
//
//    plugins {
//        id("org.springframework.boot") version springBootVersion
//        id("io.spring.dependency-management") version springDependencyManagementVersion
//        id("com.palantir.docker") version palantirDockerVersion
//
//        kotlin("jvm") version kotlinVersion
//        kotlin("plugin.spring") version kotlinVersion
//        kotlin("plugin.jpa") version kotlinVersion
//    }
//}

include(":app:app-api")

include(":core")

include(":domain:rds")
include(":domain:redis")
