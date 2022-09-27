import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.jpa")
}

tasks {
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
    implementation(project(":domain:rds"))
    implementation(project(":domain:redis"))

    implementation(project(":core:type"))
    implementation(project(":core:util"))

    implementation(project(":system:client"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
}