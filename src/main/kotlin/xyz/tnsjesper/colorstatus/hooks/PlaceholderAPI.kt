package xyz.tnsjesper.colorstatus.hooks

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import xyz.tnsjesper.colorstatus.ColorStatus

class PlaceholderAPI(colorStatus: ColorStatus) : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "colorstatus"
    }

    override fun getAuthor(): String {
        return "xyzjesper"
    }

    override fun getVersion(): String {
        return "1.0.0"
    }
}