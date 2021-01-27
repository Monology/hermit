package hermit.config.property

import org.bukkit.block.Biome

class BiomeProperty(default: Biome, configuration: PropertyHolder) : MutableProperty<Biome>(default, configuration) {
    override fun read(): Biome {
        return Biome.valueOf(configuration.yaml.getString(name)!!)
    }

    override fun set(value: Biome) {
        configuration.yaml.set(name, value.toString())
    }
}

fun PropertyHolder.biome(default: Biome) = BiomeProperty(default, this)