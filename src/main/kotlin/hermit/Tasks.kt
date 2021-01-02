package hermit

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask
import java.util.function.Consumer

inline fun launchTask(plugin: HermitPlugin, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    Bukkit.getScheduler().runTaskLater(plugin, Consumer { block(it) }, delay)
}

inline fun launchAsyncTask(plugin: HermitPlugin, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, Consumer { block(it) }, delay)
}

inline fun launchTimedTask(plugin: HermitPlugin, period: Long, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    Bukkit.getScheduler().runTaskTimer(plugin, Consumer { block(it) }, delay, period)
}

inline fun launchAsyncTimedTask(plugin: HermitPlugin, period: Long, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, Consumer { block(it) }, delay, period)
}

@JvmName("launchTaskReceiver")
inline fun HermitPlugin.launchTask(delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    return launchTask(this, delay, block)
}

@JvmName("launchAsyncTaskReceiver")
inline fun HermitPlugin.launchAsyncTask(delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    return launchAsyncTask(this, delay, block)
}

@JvmName("launchTimedTaskReceiver")
inline fun HermitPlugin.launchTimedTask(period: Long, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    return launchTimedTask(this, period, delay, block)
}

@JvmName("launchAsyncTimedTaskReceiver")
inline fun HermitPlugin.launchAsyncTimedTask(period: Long, delay: Long = 0, crossinline block: BukkitTask.() -> Unit) {
    return launchAsyncTimedTask(this, period, delay, block)
}