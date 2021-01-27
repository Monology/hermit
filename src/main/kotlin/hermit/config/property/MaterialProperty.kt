package hermit.config.property

import org.bukkit.Material

class MaterialProperty(default: Material, configuration: PropertyHolder) : MutableProperty<Material>(default, configuration) {
    override fun read(): Material {
        return Material.valueOf(configuration.yaml.getString(name)!!)
    }

    override fun set(value: Material) {
        configuration.yaml.set(name, value.toString())
    }
}

fun PropertyHolder.material(default: Material) = MaterialProperty(default, this)