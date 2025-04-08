import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "2.+"
    id("xyz.jpenilla.run-paper") version "2.+"
    kotlin("plugin.serialization") version "2.+"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.+"
    id("com.gradleup.shadow") version "8.+"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.extendedclip.com/releases/")
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}


group = properties["group"] as String
version = properties["version"] as String
description = properties["description"] as String

val gameVersion by properties
val foliaSupport = properties["foliaSupport"] as String == "true"
val projectName = properties["name"] as String


dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.+")


    implementation("net.kyori", "adventure-text-minimessage", "4.+")

    implementation("dev.jorel:commandapi-bukkit-kotlin:10.+")
    compileOnly("dev.jorel:commandapi-bukkit-core:10.+")
    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:10.+")

    compileOnly("me.clip:placeholderapi:2.11.6")

}

kotlin {
    jvmToolchain(21)
}

tasks {
    runServer {
        minecraftVersion("1.21.1")
    }
    assemble {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "21"
    }
}

tasks.jar {
    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }
}

bukkit {
    main = "$group.${projectName.lowercase()}.${projectName}"
    apiVersion = "1.20"
    foliaSupported = foliaSupport
    website = "https://modrinth.com/plugin/color-status"
    authors = listOf("xyzjesper")

    // Optionals
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    depend = listOf()
    softDepend = listOf("PlaceholderAPI")
    libraries = listOf()
}