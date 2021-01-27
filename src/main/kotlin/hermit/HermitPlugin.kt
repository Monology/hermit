package hermit

import hermit.command.CommandMessages
import hermit.command.HermitCommand
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

abstract class HermitPlugin(var shouldAutoCreateConfig: Boolean = true) : JavaPlugin() {
    var fallbackPrefix = name.decapitalize()
        protected set

    var commandMessages = CommandMessages()
        protected set

    val configFile = dataFolder.child("config.yml")

    val commands = mutableListOf<HermitCommand>()

    open fun enable() {}

    open fun disable() {}

    open fun load() {}

    final override fun onEnable() {
        if (shouldAutoCreateConfig && getResource("config.yml") != null) {
            saveResource("config.yml", false)
        }

        enable()
    }

    final override fun onDisable() {
        disable()
    }

    final override fun onLoad() {
        load()
    }

    fun loadProperties(string: String) = Properties().apply {
        load(File(dataFolder, string).inputStream())
    }

    fun loadYml(string: String): YamlConfiguration {
        val file = File(dataFolder, string)
        if (!file.exists()) file.createNewFile()
        require(file.isDirectory) {
            "The file \"$string\" is a directory and therefore cannot be loaded as a YML configuration."
        }

        return YamlConfiguration.loadConfiguration(file)
    }

    fun PluginRegistrable.register() {
        register(this@HermitPlugin)
    }
}