package xyz.tnsjesper.colorstatus.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import xyz.tnsjesper.colorstatus.config.ConfigManager

object PlayerJoinEvent : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        val playerData = ConfigManager.playerdata.player[player.uniqueId.toString()]

        player.setDisplayName(player.name)
        player.setPlayerListName(player.name)

        if (playerData?.prefix?.length == 0) {
            player.setDisplayName(playerData?.name + player.name)
            player.setPlayerListName(playerData?.name + player.name)
        } else {
            player.setDisplayName(playerData?.prefix + " " + playerData?.name + player.name)
            player.setPlayerListName(playerData?.prefix + " " + playerData?.name + player.name)
        }


    }

}