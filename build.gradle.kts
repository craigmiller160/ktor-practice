val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val junit_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("com.diffplug.spotless") version "6.15.0"
}

group = "io.craigmiller160"
version = "0.0.1"
application {
    mainClass.set("io.craigmiller160.KtorPracticeRunnerKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version") // TODO not sure if I need this
    implementation("io.ktor:ktor-client-content-negotiation-jvm:$ktor_version") // TODO not sure if I need this
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-config-yaml-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.flywaydb:flyway-core:9.15.0")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("io.insert-koin:koin-ktor:3.3.1")
    testImplementation("io.craigmiller160:testcontainers-common:1.2.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

}

spotless {
    kotlin {
        ktfmt()
    }
}

fun loadEnv(file: String, op: (String,String) -> Unit) {
    File(file).readLines().forEach { line ->
        val (key, value) = line.split("=")
        op(key, value)
    }
}

tasks.withType<JavaExec> {
    loadEnv(".env.dev") { key, value ->
        environment(key, value)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    loadEnv(".env.test") { key, value ->
        environment(key, value)
    }
}