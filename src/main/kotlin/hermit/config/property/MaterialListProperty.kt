package hermit.config.property

import org.bukkit.Material

class MaterialListProperty(default: List<Material>, configuration: PropertyHolder) : MutableProperty<List<Material>>(default, configuration) {
    override fun read(): List<Material> {
        return configuration.yaml.getStringList(name).map(Material::valueOf)
    }

    override fun set(value: List<Material>) {
        configuration.yaml.set(name, value.map(Material::toString))
    }
}

fun PropertyHolder.materialList(default: List<Material>) = MaterialListProperty(default, this)