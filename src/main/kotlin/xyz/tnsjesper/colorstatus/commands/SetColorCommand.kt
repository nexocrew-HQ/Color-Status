package xyz.tnsjesper.colorstatus.commands

import dev.jorel.commandapi.kotlindsl.*
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.apache.logging.log4j.message.StructuredDataId
import org.bukkit.ChatColor
import xyz.tnsjesper.colorstatus.config.ConfigManager
import xyz.tnsjesper.colorstatus.config.loadConfig
import java.io.File


class SetColorCommand {

    private val file = File("plugin/Color-Status/data/playerdata.json")
    private val data: MutableMap<String, PlayerData> = file.loadConfig(mutableMapOf())


    val mm = MiniMessage.miniMessage()

    val command = commandTree("cosmetics") {

        literalArgument("reset") {
            playerExecutor { player, args ->

                val displayName: String = player.name
                player.setDisplayName(displayName)
                player.setPlayerListName(displayName)

                val parsed: Component =
                    mm.deserialize(ConfigManager.settings.resetmessage)
                player.sendMessage(parsed)
            }
        }


        literalArgument("set") {
            literalArgument("color") {

                chatColorArgument("color") {


                    literalArgument("status") {
                        stringArgument("status") {

                            literalArgument("bold") {
                                booleanArgument("bold") {

                                    playerExecutor { player, args ->

                                        if (args.get(2) == true) {
                                            val displayName: String =
                                                "[" + args.get(0) + ChatColor.BOLD + args.get(1) + ChatColor.WHITE + "]" + " " + player.name
                                            player.setDisplayName(displayName)
                                            player.setPlayerListName(displayName)

                                            val parsed: Component =
                                                mm.deserialize(ConfigManager.settings.setmessage)

                                            data.put(
                                                player.uniqueId.toString(),
                                                PlayerData(
                                                    displayName
                                                )
                                            )


                                            player.sendMessage(parsed)

                                        } else {

                                            val displayName: String =
                                                "[" + ChatColor.BOLD + args.get(0) + args.get(1) + ChatColor.WHITE + "]" + " " + player.name
                                            player.setDisplayName(displayName)
                                            player.setPlayerListName(displayName)


                                            val parsed: Component =
                                                mm.deserialize(ConfigManager.settings.setmessage)

                                            player.sendMessage(parsed)
                                        }

                                    }

                                }
                            }
                        }

                    }
                }
            }
        }

    }
}

@Serializable
data class PlayerData(val prefix: String) {
    override fun toString() = "$prefix"
}

