import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"

    id("org.jlleitschuh.gradle.ktlint") version "11.0.0" apply false

    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21" apply false
//    kotlin("plugin.jpa") version "1.6.21" apply false

}

java.sourceCompatibility = JavaVersion.VERSION_17

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
        plugin("org.springframework.boot")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {

        // Kotlin Standard Library
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

//        testImplementation("io.mockk:mockk:$mockkVersion")
//        testImplementation("io.strikt:strikt-core:$striktVersion")
        testImplementation("com.ninja-squad:springmockk:3.1.0")
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
