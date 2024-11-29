package xyz.tnsjesper.colorstatus.config

import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugins/ColorStatus/config.json")
    private val playerFile = File("plugins/ColorStatus/data/playerdata.json")

    val settings = settingsFile.loadConfig(SettingsData())
    val playerdata = playerFile.loadConfig(PlayerData())

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
        playerFile.writeText(json.encodeToString(playerdata))
    }

}