package xyz.tnsjesper.colorstatus.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}