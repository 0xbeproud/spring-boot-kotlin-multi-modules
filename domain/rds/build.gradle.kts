import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    kotlin("plugin.noarg")
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
    api("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.flywaydb:flyway-core")

    implementation("com.h2database:h2")

    runtimeOnly("com.h2database:h2")
//    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("com.h2database:h2")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}
