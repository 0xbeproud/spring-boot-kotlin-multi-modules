import org.flywaydb.gradle.task.FlywayMigrateTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    kotlin("plugin.noarg")

    id("org.flywaydb.flyway")
}

task<FlywayMigrateTask>("flywayMigrateDocker") {
    driver = "com.mysql.cj.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&charaterEncoding=utf-8"
    user = "root"
    password = "root123"
}

flyway {
    driver = "com.mysql.cj.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&charaterEncoding=utf-8"
    user = "root"
    password = "root123"
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
    implementation("org.flywaydb:flyway-mysql")

    implementation("com.h2database:h2")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

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
