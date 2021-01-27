package hermit.config.property

import org.bukkit.block.Biome

class BiomeListProperty(default: List<Biome>, configuration: PropertyHolder) : MutableProperty<List<Biome>>(default, configuration) {
    override fun read(): List<Biome> {
        return configuration.yaml.getStringList(name).map(Biome::valueOf)
    }

    override fun set(value: List<Biome>) {
        configuration.yaml.set(name, value.map(Biome::toString))
    }
}

fun PropertyHolder.biomeList(default: List<Biome>) = BiomeListProperty(default, this)