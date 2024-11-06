plugins {
    kotlin("jvm") version "2.0.21"
    id("io.papermc.paperweight.userdev") version "1.+"
    id("xyz.jpenilla.run-paper") version "2.+"
    kotlin("plugin.serialization") version "2.0.21"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "xyz.tnsjesper"
version = "0.3.0"

repositories {
    mavenCentral()
}


group = properties["group"] as String
version = properties["version"] as String
description = properties["description"] as String

val gameVersion by properties
val foliaSupport = properties["foliaSupport"] as String == "true"
val projectName = properties["name"] as String


dependencies {
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.+")
    implementation("de.miraculixx", "kpaper", "1.+")
    implementation("dev.jorel", "commandapi-bukkit-shade", "9.+")
    implementation("net.kyori", "adventure-text-minimessage", "4.+")
    implementation("dev.jorel", "commandapi-bukkit-kotlin", "9.+")

}

kotlin {
    jvmToolchain(17)
}

tasks {
    runServer {
        minecraftVersion("1.20.1")
    }
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

bukkit {
    main = "$group.${projectName.lowercase()}.${projectName}"
    apiVersion = "1.16"
    foliaSupported = foliaSupport
    website = "https://modrinth.com/plugin/color-status"
    authors = listOf("xyzjesper")

    // Optionals
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    depend = listOf()
    softDepend = listOf()
    libraries = listOf()
}