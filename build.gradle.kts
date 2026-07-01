plugins {
    kotlin("jvm") version "2.2.21"
    id("com.gradleup.shadow") version "9.2.2"
}

group = "dev.l1cked.superclearchat"
version = "1.0.0"

val pluginVersion = version.toString()
val pluginDescription = "Simple optimized chat clearing command."

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(
                "version" to pluginVersion,
                "description" to pluginDescription,
            )
        }
    }

    shadowJar {
        archiveClassifier.set("")
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}
