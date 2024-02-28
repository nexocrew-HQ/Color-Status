plugins {
    kotlin("jvm") version "1.9.22"
    id("io.papermc.paperweight.userdev") version "1.5.10"
    id("xyz.jpenilla.run-paper") version "2.2.2"

}

group = "xyz.tnsjesper"
version = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {

    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")

    implementation("de.miraculixx", "kpaper", "1.1.2")
    implementation("dev.jorel", "commandapi-bukkit-shade", "9.3.0")
    implementation("net.kyori", "adventure-text-minimessage", "4.16.0")
    implementation("dev.jorel", "commandapi-bukkit-kotlin", "9.3.0")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}