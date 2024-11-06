package xyz.tnsjesper.colorstatus.config

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val resetmessage: String = "Your prefix has beed reset!",
    val setmessage: String = "Your prefix has been set!",
)
