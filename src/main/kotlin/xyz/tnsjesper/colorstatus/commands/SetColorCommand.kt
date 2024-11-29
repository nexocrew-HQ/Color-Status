package xyz.tnsjesper.colorstatus.commands

import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.ChatColor
import xyz.tnsjesper.colorstatus.config.ConfigManager
import xyz.tnsjesper.colorstatus.config.PlayerConfig


class SetColorCommand {




    val mm = MiniMessage.miniMessage()

    val command = commandTree("cosmetics") {
        literalArgument("reset") {
            withPermission("colorstatus.command.cosmetics.reset")
            literalArgument("prefix") {
                withPermission("colorstatus.command.cosmetics.reset.prefix")
                playerExecutor { player, args ->

                    val data = ConfigManager.playerdata.player[player.uniqueId.toString()]

                    player.setDisplayName(data?.name + player.name)
                    player.setPlayerListName(data?.name + player.name)

                    val parsed: Component = mm.deserialize(ConfigManager.settings.prefix_resetmessage)
                    player.sendMessage(parsed)
                }
            }
            literalArgument("name") {
                withPermission("colorstatus.command.cosmetics.reset.name")
                playerExecutor { player, args ->

                    val data = ConfigManager.playerdata.player[player.uniqueId.toString()]

                    player.setDisplayName(data?.prefix + " " + player.name)
                    player.setPlayerListName(data?.prefix + " " + player.name)

                    val parsed: Component = mm.deserialize(ConfigManager.settings.name_resetmessage)
                    player.sendMessage(parsed)
                }
            }

        }

        literalArgument("prefix") {
            withPermission("colorstatus.command.cosmetics.prefix")
            literalArgument("color") {
                withPermission("colorstatus.command.cosmetics.prefix.color")
                chatColorArgument("color") {
                    literalArgument("status") {
                        withPermission("colorstatus.command.cosmetics.prefix.status")
                        stringArgument("status") {
                            literalArgument("bold") {
                                withPermission("colorstatus.command.cosmetics.prefix.bold")
                                booleanArgument("bold") {
                                    playerExecutor { player, args ->

                                        if (args.get(2) == true) {
                                            val displayName: String =
                                                "[" + args.get(0) + ChatColor.BOLD + args.get(1) + ChatColor.WHITE + "]"

                                            val parsed: Component =
                                                mm.deserialize(ConfigManager.settings.prefix_setmessage)

                                            ConfigManager.playerdata.player["${player.uniqueId}"] = PlayerConfig(
                                                prefix = displayName,
                                                name = ConfigManager.playerdata.player["${player.uniqueId}"]?.name
                                                )
                                            ConfigManager.save()

                                            val data = ConfigManager.playerdata.player["${player.uniqueId}"]
                                            player.setDisplayName(data?.prefix + " " + data?.name + player.name)
                                            player.setPlayerListName(data?.prefix + " " + data?.name + player.name)

                                            player.sendMessage(parsed)

                                        } else {

                                            val displayName: String =
                                                "[" + args.get(0) + args.get(1) + ChatColor.WHITE + "]"

                                            val parsed: Component =
                                                mm.deserialize(ConfigManager.settings.prefix_setmessage)

                                            ConfigManager.playerdata.player["${player.uniqueId}"] = PlayerConfig(
                                                prefix = displayName,
                                                name = ConfigManager.playerdata.player["${player.uniqueId}"]?.name
                                            )
                                            ConfigManager.save()

                                            val data = ConfigManager.playerdata.player["${player.uniqueId}"]
                                            player.setDisplayName(data?.prefix + " " + data?.name + player.name)
                                            player.setPlayerListName(data?.prefix + " " + data?.name + player.name)

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
        literalArgument("name") {
            withPermission("colorstatus.command.cosmetics.name")
            literalArgument("color") {
                withPermission("colorstatus.command.cosmetics.name.color")
                chatColorArgument("color") {
                    literalArgument("bold") {
                        withPermission("colorstatus.command.cosmetics.name.bold")
                        booleanArgument("bold") {
                            playerExecutor { player, args ->

                                if (args.get(1) == true) {
                                    val displayName: String = "" + args.get(0) + "§l"

                                    val parsed: Component = mm.deserialize(ConfigManager.settings.name_setmessage)

                                    ConfigManager.playerdata.player["${player.uniqueId}"] = PlayerConfig(
                                        name = displayName,
                                        prefix = ConfigManager.playerdata.player["${player.uniqueId}"]?.prefix
                                    )
                                    ConfigManager.save()

                                    val data = ConfigManager.playerdata.player["${player.uniqueId}"]
                                    if (data?.prefix?.length == 0) {
                                        player.setDisplayName(data?.name + player.name)
                                        player.setPlayerListName(data?.name + player.name)
                                    } else {
                                        player.setDisplayName(data?.prefix + " " + data?.name + player.name)
                                        player.setPlayerListName(data?.prefix + " " + data?.name + player.name)
                                    }


                                    player.sendMessage(parsed)

                                } else {

                                    val displayName: String = "" + args.get(0)

                                    val parsed: Component = mm.deserialize(ConfigManager.settings.name_setmessage)

                                    ConfigManager.playerdata.player["${player.uniqueId}"] =
                                        PlayerConfig(
                                            name = displayName,
                                            prefix = ConfigManager.playerdata.player["${player.uniqueId}"]?.prefix
                                        )
                                    ConfigManager.save()

                                    val data = ConfigManager.playerdata.player["${player.uniqueId}"]
                                    if (data?.prefix?.length == 0) {
                                        player.setDisplayName(data?.name + player.name)
                                        player.setPlayerListName(data?.name + player.name)
                                    } else {
                                        player.setDisplayName(data?.prefix + " " + data?.name + player.name)
                                        player.setPlayerListName(data?.prefix + " " + data?.name + player.name)
                                    }


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



