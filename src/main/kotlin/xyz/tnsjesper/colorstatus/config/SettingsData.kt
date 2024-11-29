package xyz.tnsjesper.colorstatus.config

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    var prefix_resetmessage: String = "<color:#94c8ff>You are now back to your old self and no longer have a prefix.</color>",
    var prefix_setmessage: String = "<color:#ffb0de>Your new prefix has been set. And you can now glisten with your new cosmetics</color>",
    var name_resetmessage: String = "<color:#94c8ff>You are now back to your old self and no longer have a custom name.</color>",
    var name_setmessage: String = "<color:#ffb0de>Your new name has been set. And you can now glisten with your new cosmetics</color>",
)

@Serializable
data class PlayerData(var player: MutableMap<String, PlayerConfig> = mutableMapOf())

@Serializable
data class PlayerConfig(val prefix: String? = "", val name: String? = "")