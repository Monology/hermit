package hermit.config.property

import org.bukkit.configuration.ConfigurationSection

abstract class PropertyHolder {
    internal abstract val yaml: ConfigurationSection

    internal abstract val properties: MutableSet<Property<*>>
}