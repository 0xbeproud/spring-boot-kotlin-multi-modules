rootProject.name = "spring-boot-kotlin-multi-modules"

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion

        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
    }
}

include(":app:app-api")
include(":app:app-auth")
include(":app:app-batch")

include(":core:type")
include(":core:util")

include(":system:client")

include(":domain:rds")
include(":domain:redis")