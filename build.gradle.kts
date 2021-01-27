import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    `maven-publish`
    signing
    maven
}

group = "hermit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.codemc.io/repository/maven-public/")
}

dependencies {
    shadow(kotlin("stdlib"))
    implementation("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}

dependencies {
    implementation(kotlin("stdlib"))
}

java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = JavaVersion.VERSION_11
    sourceCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = rootProject.name
            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }

                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

            pom {
                name.set(rootProject.name)
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = uri("https://repo.potatocorp.dev/storages/maven/monology")
            val snapshotsRepoUrl = uri("https://repo.potatocorp.dev/storages/maven/monology")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)
        }

        mavenLocal()
    }
}


tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}