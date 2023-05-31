project.setProperty("mainClassName", "MainKt")

plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("io.klogging:klogging-jvm:0.4.13")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

tasks.findByName("shadowJar")!!.apply {
    this.dependsOn(tasks.jar)
}

application {
    mainClass.set("MainKt")
}