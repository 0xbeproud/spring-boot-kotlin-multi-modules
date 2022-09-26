import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
//    kotlin("plugin.jpa")
}
tasks {
    withType<BootJar> {
        archiveClassifier.set("boot")
    }

    withType<Jar> {
        enabled = false
    }
}

dependencies {
    implementation(project(":domain:rds"))
//    implementation(project(":domain:redis"))

    implementation(project(":core:type"))
    implementation(project(":core:util"))

    implementation("org.springframework.boot:spring-boot-starter-batch")
//    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.springframework.kafka:spring-kafka")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")

    testImplementation("org.springframework.batch:spring-batch-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

