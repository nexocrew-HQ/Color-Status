package xyz.tnsjesper.colorstatus

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import xyz.tnsjesper.colorstatus.commands.SetColorCommand
import xyz.tnsjesper.colorstatus.config.ConfigManager
import xyz.tnsjesper.colorstatus.events.PlayerJoinEvent
import xyz.tnsjesper.colorstatus.hooks.PlaceholderAPI


class ColorStatus : JavaPlugin() {
    companion object {
        lateinit var instance: ColorStatus
    }

    init {
        instance = this
    }

    override fun onEnable() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).silentLogs(true))
        CommandAPI.onEnable();

//        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
//            PlaceholderAPI(this).register();
//            logger.info("[ColorStatus] Hooking into PlaceholderAPI");
//        }

        server.consoleSender.sendMessage(Component.text("[ColorStatus] Load ColorStatus"))

        // Configs
        val settings = ConfigManager.settings
        val playerdata = ConfigManager.playerdata

        // Commands
        SetColorCommand()

        // Events
        server.pluginManager.registerEvents(PlayerJoinEvent, this)

    }

    override fun onDisable() {
        CommandAPI.onDisable()
        server.consoleSender.sendMessage(Component.text("[ColorStatus] Plugin disabled"))
    }

}


