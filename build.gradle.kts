import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false

    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0" apply false
    id("org.flywaydb.flyway") version "9.3.1" apply false
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.betheproud"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-kapt")
//
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("org.jlleitschuh.gradle.ktlint-idea")
    }

    dependencies {

        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

//        implementation("org.springframework.boot:spring-boot-starter")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

//        testImplementation("io.mockk:mockk:$mockkVersion")
//        testImplementation("io.strikt:strikt-core:$striktVersion")
//        testImplementation("com.ninja-squad:springmockk:3.1.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
            exclude(module = "mockito-core")
        }
    }

    // ktlint setting 3
//    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
//        verbose.set(true)
//        disabledRules.set(
//                setOf(
//                        "import-ordering",
//                        "no-wildcard-imports",
//                        "final-newline",
//                        "insert_final_newline",
//                        "max_line_length"
//                )
//        )
//    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}