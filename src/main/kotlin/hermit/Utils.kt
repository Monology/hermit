package hermit

import hermit.gui.GUI
import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException
import java.net.URL
import java.text.DecimalFormat
import java.util.*
import kotlin.random.Random

operator fun ChatColor.plus(other: String) = this.toString() + other

operator fun ChatColor.plus(other: ChatColor) = this.toString() + other.toString()

inline val String.colored
    get() = ChatColor.translateAlternateColorCodes('&', this)

val Byte.ordinal: String
    get() = if (this in 10..20) toString() + "th" else (this % 10).run {
        this@ordinal.toString() + when (this) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

val Short.ordinal: String
    get() = if (this in 10..20) toString() + "th" else (this % 10).run {
        this@ordinal.toString() + when (this) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

val Int.ordinal: String
    get() = if (this in 10..20) toString() + "th" else (this % 10).run {
        this@ordinal.toString() + when (this) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

val Long.ordinal: String
    get() = if (this in 10..20) toString() + "th" else (this % 10).run {
        this@ordinal.toString() + when (this) {
            1L -> "st"
            2L -> "nd"
            3L -> "rd"
            else -> "th"
        }
    }

fun String.plural(num: Int, plural: String = (if (this.endsWith("s")) this + "es" else this + "s")): String {
    return if (num == 1) this else plural
}

val Long.seconds get() = this * 20
val Long.minutes get() = seconds * 60
val Long.hours get() = minutes * 60
val Long.days get() = hours * 24
val Long.weeks get() = days * 7
val Long.months get() = days * 30
val Long.years get() = days * 365

val Int.seconds get() = this * 20L
val Int.minutes get() = seconds * 60L
val Int.hours get() = minutes * 60L
val Int.days get() = hours * 24L
val Int.weeks get() = days * 7L
val Int.months get() = days * 30L
val Int.years get() = days * 365L

val Double.seconds get() = this * 20
val Double.minutes get() = seconds * 60
val Double.hours get() = minutes * 60
val Double.days get() = hours * 24
val Double.weeks get() = days * 7
val Double.months get() = days * 30
val Double.years get() = days * 365

fun CommandSender.msg(any: Any?) = sendMessage(any.toString())

fun CommandSender.msg(any: Array<Any?>?) {
    if (any == null) sendMessage(any.toString()) else any.forEach {
        sendMessage(it.toString())
    }
}

val onlinePlayers: Collection<Player> get() = Bukkit.getOnlinePlayers()

fun Iterable<Player>.withPermission(permission: String) =
    filter { it.hasPermission(permission) }

fun Iterable<Player>.withPermissionOrOp(permission: String?)
    = filter { player -> permission?.let { player.hasPermission(it) } ?: player.isOp }

fun checkForUpdates(plugin: HermitPlugin, id: Int, message: String, permission: String? = null) {
    checkForUpdates(plugin, id) {
        if (it) {
            onlinePlayers
                .withPermissionOrOp(permission)
                .forEach { player -> player.sendMessage(message) }
        }
    }
}

inline fun checkForUpdates(plugin: HermitPlugin, id: Int, crossinline predicate: (Boolean) -> Unit) {
    launchAsyncTask(plugin) {
        try {
            URL("https://api.spigotmc.org/legacy/update.php?resource=$id").openStream()
                .use {
                    Scanner(it).use { scanner ->
                        predicate(scanner.hasNext())
                    }
                }
        } catch (exception: IOException) {
            Bukkit.getServer().logger.warning("There was an error retrieving the update-status of a plugin: ${plugin.name}")
        }
    }
}

@JvmName("checkForUpdatesReceiver")
fun HermitPlugin.checkForUpdates(id: Int, message: String, permission: String? = null) {
    checkForUpdates(this, id, message, permission)
}

@JvmName("checkForUpdatesReceiver")
inline fun HermitPlugin.checkForUpdates(id: Int, crossinline predicate: (Boolean) -> Unit) {
    checkForUpdates(this, id, predicate)
}

inline class HermitStringBuilder(val stringBuilder: StringBuilder) {
    inline operator fun Any?.unaryPlus() {
        stringBuilder.append(toString().colored)
    }

    inline operator fun Any?.unaryMinus() {
        stringBuilder.appendLine(toString().colored)
    }
}

fun ClosedFloatingPointRange<Double>.name() = when {
    start == Double.MIN_VALUE && endInclusive == Double.MAX_VALUE -> "a decimal"
    start == Double.MIN_VALUE -> "a decimal less than ${endInclusive.toFormattedString()}"
    else -> "a decimal greater than ${start.toFormattedString()}"
}

fun LongRange.name() = when {
    start == Long.MIN_VALUE -> "less than ${endInclusive.toFormattedString()}"
    endInclusive == Long.MAX_VALUE -> "greater than $start"
    else -> "between ${start.toFormattedString()} and ${endInclusive.toFormattedString()}"
}

fun String.toUUID(): UUID {
    return UUID.fromString(this)
}

internal fun internalError(message: String): AssertionError {
    return AssertionError("$message Please create an issue on GitHub.")
}

fun Double.toFormattedString(): String {
    return String.format("%,d", this)
}

fun Long.toFormattedString(): String {
    return String.format("%,d", this)
}

fun File.child(child: String) = File(this, child)

fun World?.location(x: Double, y: Double, z: Double) = Location(this, x, y, z)

fun Location.maxYBlock() = world.location(x, 256.0, z).block
fun Location.minYBlock() = world.location(x, 0.0, z).block

fun Location.highestBlockInColumn() = world?.getHighestBlockAt(x.toInt(), z.toInt())

tailrec fun World.findRandomLocation(minX: Double, maxX: Double, minZ: Double, maxZ: Double, biomes: List<Biome>): Location {
    val x = Random.nextDouble(minX, maxX)
    val z = Random.nextDouble(minZ, maxZ)
    val location = location(x, 256.0, z)

    if (location.block.biome in biomes) {
        return findRandomLocation(minX, maxX, minZ, maxZ, biomes)
    }

   return location.highestBlockInColumn()?.location ?: return findRandomLocation(minX, maxX, minZ, maxZ, biomes)
}