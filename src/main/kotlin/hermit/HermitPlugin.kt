package hermit

import hermit.command.CommandMessages
import hermit.command.HermitCommand
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

abstract class HermitPlugin(var shouldAutoCreateConfig: Boolean = true) : JavaPlugin() {
    var fallbackPrefix = name.decapitalize()
        protected set

    var commandMessages = CommandMessages()
        protected set

    val commands = mutableListOf<HermitCommand>()

    open fun enable() {}

    open fun disable() {}

    open fun load() {}

    override fun onEnable() {
        if (shouldAutoCreateConfig && getResource("config.yml") != null) {
            saveResource("config.yml", false)
        }

        enable()
    }

    val config = HermitConfiguration(file)

    override fun onDisable() {
        disable()
    }

    override fun onLoad() {
        load()
        getConfig()
    }

    fun loadProperties(string: String) = Properties().apply {
        load(File(dataFolder, string).inputStream())
    }

    fun loadYml(string: String): HermitConfiguration {
        val file = File(dataFolder, string)
        if (!file.exists()) file.createNewFile()
        require(file.isDirectory) {
            "The file \"$string\" is a directory and therefore cannot be loaded as a YML configuration."
        }

        return HermitConfiguration(file)
    }

    fun HermitCommand.register() {
        register(this@HermitPlugin)
        commands.add(this)
    }
}