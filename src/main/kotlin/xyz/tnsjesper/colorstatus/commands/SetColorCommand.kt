package xyz.tnsjesper.colorstatus.commands

import dev.jorel.commandapi.kotlindsl.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.ChatColor


class SetColorCommand {

    val mm = MiniMessage.miniMessage()

    val command = commandTree("cosmetics") {

        literalArgument("reset") {
            playerExecutor { player, args ->

                val displayName: String = player.name
                player.setDisplayName(displayName)
                player.setPlayerListName(displayName)

                val parsed: Component =
                    mm.deserialize("<color:gray>Dein Prefix wurde Resetet")
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
                                                mm.deserialize("<color:gray>Dein Prefix wurde gesetzt.")



                                            player.sendMessage(parsed)

                                        } else {

                                            val displayName: String =
                                                "[" + ChatColor.BOLD + args.get(0) + args.get(1) + ChatColor.WHITE + "]" + " " + player.name
                                            player.setDisplayName(displayName)
                                            player.setPlayerListName(displayName)

                                            println(player.name)
                                            println(displayName)

                                            val parsed: Component =
                                                mm.deserialize("<color:gray>Dein Prefix wurde gesetzt.")

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

