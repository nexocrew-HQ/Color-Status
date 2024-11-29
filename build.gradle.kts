import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev") version "1.+"
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
}


group = properties["group"] as String
version = properties["version"] as String
description = properties["description"] as String

val gameVersion by properties
val foliaSupport = properties["foliaSupport"] as String == "true"
val projectName = properties["name"] as String


dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.+")


    implementation("net.kyori", "adventure-text-minimessage", "4.+")

    implementation("dev.jorel:commandapi-bukkit-kotlin:9.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly("dev.jorel:commandapi-bukkit-core:9.6.0")
    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:9.6.0")

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
        dependsOn(reobfJar)
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

bukkit {
    main = "$group.${projectName.lowercase()}.${projectName}"
    apiVersion = "1.16"
    foliaSupported = foliaSupport
    website = "https://modrinth.com/plugin/color-status"
    authors = listOf("xyzjesper")

    // Optionals
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    depend = listOf()
    softDepend = listOf("PlaceholderAPI")
    libraries = listOf()
}