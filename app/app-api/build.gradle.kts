import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
//    id("org.springframework.boot") version "2.7.3"
//    id("io.spring.dependency-management") version "1.0.13.RELEASE"
//    kotlin("jvm") version "1.6.21"
//    kotlin("plugin.spring") version "1.6.21"
}

tasks {
//    named<BootJar>("bootJar") {
//        mainClass.set("com.beproud.appapi.AppApiAppcation")
//    }

    withType<BootJar> {
        archiveClassifier.set("boot")
    }

    withType<Jar> {
        enabled = false
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}