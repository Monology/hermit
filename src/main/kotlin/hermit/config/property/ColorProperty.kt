package hermit.config.property

import org.bukkit.Color

class ColorProperty(default: Color, configuration: PropertyHolder) : MutableProperty<Color>(default, configuration) {
    override fun read() = configuration.yaml.getColor(name)!!
}

fun PropertyHolder.color(default: Color) = ColorProperty(default, this)