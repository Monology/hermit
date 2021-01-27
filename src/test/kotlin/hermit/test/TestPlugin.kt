package hermit.test

import hermit.HermitPlugin
import hermit.command.command
import hermit.config.HermitConfiguration
import hermit.config.property.*
import hermit.findRandomLocation
import hermit.msg
import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File

class WildPluginConfiguration(file: File) : HermitConfiguration(file) {
    class WorldSection : Section() {
        var isEnabled by boolean(false)
        var minX by double(-500.0)
        var maxX by double(500.0)
        var minZ by double(-500.0)
        var maxZ by double(500.0)
        var blacklistedBiomes by stringList(emptyList())
    }

    var notEnabled by string("&cWild is not enabled in this world.")
    var teleportationMessage by string("&9You have been teleported successfully.")
    val overworld by section(WorldSection())
    val nether by section(WorldSection())
    val theEnd by section(WorldSection())
    val worlds = mapOf(
        World.Environment.NORMAL to overworld,
        World.Environment.NETHER to nether,
        World.Environment.THE_END to theEnd
    )
}

class WildPlugin : HermitPlugin() {
    val config = WildPluginConfiguration(configFile)

    override fun enable() {
        command("wild") {
            permission = "wild.use"

            sub("reload") {
                permission = "wild.reload"

                execute<CommandSender> {
                    config.reload()
                }
            }

            execute<Player> {
                val section = config.worlds[sender.world.environment]!!

                if (!section.isEnabled) {
                    return@execute sender.msg(config.notEnabled)
                }

                sender.teleport(sender.world.findRandomLocation(
                    section.minX, section.maxX,
                    section.minZ, section.maxZ,
                    section.blacklistedBiomes.map(Biome::valueOf)
                ))
                sender.msg(config.teleportationMessage)
            }
        }.register()
    }
}