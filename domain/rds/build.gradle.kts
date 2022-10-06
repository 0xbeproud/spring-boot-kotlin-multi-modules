import org.flywaydb.gradle.task.FlywayMigrateTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    kotlin("plugin.noarg")
    kotlin("kapt")

    id("org.flywaydb.flyway")
}

task<FlywayMigrateTask>("flywayMigrateDocker") {
    driver = "com.mysql.cj.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&charaterEncoding=utf-8"
    user = "root"
    password = "root123"
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

tasks {
    withType<BootJar> {
        enabled = false
    }

    withType<Jar> {
        enabled = true
    }
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

dependencies {
    val querydslVersion = "5.0.0"

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa")
    implementation("org.flywaydb:flyway-mysql")
    implementation("com.h2database:h2")

    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("com.h2database:h2")
}

