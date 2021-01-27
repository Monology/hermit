package hermit.config.property

import org.bukkit.OfflinePlayer

class OfflinePlayerProperty(default: OfflinePlayer, configuration: PropertyHolder) : MutableProperty<OfflinePlayer?>(default, configuration) {
    override fun read() = configuration.yaml.getOfflinePlayer(name)
}

fun PropertyHolder.offlinePlayer(default: OfflinePlayer) = OfflinePlayerProperty(default, this)