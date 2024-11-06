package xyz.tnsjesper.colorstatus.config

import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {

    private val settingsFile = File("plugin/Color-Status/config.json")
    private val configurableList = mutableListOf<Configurable>()

    val settings = settingsFile.loadConfig(SettingsData())

    fun addConfigurable(configurable: Configurable) {
        configurableList.add(configurable)
        configurable.load()
    }

    fun save() {
        settingsFile.writeText(json.encodeToString(settings))
        configurableList.forEach { it.save() }
    }

}