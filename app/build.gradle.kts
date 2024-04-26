plugins {
    alias(libs.plugins.jvm)
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.postgresql.driver)
    implementation(libs.hikaricp)
    implementation(libs.flyway)
    implementation(libs.flyway.postgres)

    testImplementation(libs.testcontainer.postgresql)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.example.AppKt"
}
