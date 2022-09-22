import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
//    id("org.springframework.boot") version "2.7.3"
//    id("io.spring.dependency-management") version "1.0.13.RELEASE"
//    kotlin("jvm") version "1.6.21"
//    kotlin("plugin.spring") version "1.6.21"
}

tasks {
    withType<BootJar> {
        enabled = false
    }

    withType<Jar> {
        enabled = true
    }
}

dependencies {
}
