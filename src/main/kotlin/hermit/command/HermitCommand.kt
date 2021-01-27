package hermit.command

import hermit.HermitPlugin
import hermit.PluginRegistrable
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand

class HermitCommand(
    label: String,
    aliases: Set<String>,
    description: String,
    permission: String?,
    baseUsage: String,
    usages: List<String>,
    private val tabCompleter: ((CommandSender, Array<String>) -> List<String>)?,
    private val execute: (CommandSender, Array<String>) -> Unit
) : BukkitCommand(label, description, baseUsage, aliases.toList()), PluginRegistrable {
    override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
        execute(sender, args)
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<String>): List<String> {
        return tabCompleter?.invoke(sender, args) ?: super.tabComplete(sender, alias, args)
    }

    override fun register(plugin: HermitPlugin) {
        commandMap.register(plugin.fallbackPrefix, label, this)
        plugin.commands.add(this)
    }

    init {
        this.description = description
        this.usage = "/$baseUsage"
        this.permission = permission
    }

    companion object {
        private val commandMap = Server::class.java
            .getDeclaredField("commandMap")
            .apply { isAccessible = true }
            .get(Bukkit.getServer()) as CommandMap
    }
}