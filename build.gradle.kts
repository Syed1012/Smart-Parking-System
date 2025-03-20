plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.sps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
