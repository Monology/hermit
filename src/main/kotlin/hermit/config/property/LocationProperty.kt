package hermit.config.property

import org.bukkit.Location

class LocationProperty(default: Location, configuration: PropertyHolder) : MutableProperty<Location>(default, configuration) {
    override fun read() = configuration.yaml.getLocation(name)!!
}

fun PropertyHolder.location(default: Location) = LocationProperty(default, this)