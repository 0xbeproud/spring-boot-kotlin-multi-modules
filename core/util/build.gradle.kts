import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
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
    implementation(project(":core:type"))
}