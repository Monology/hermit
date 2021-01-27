package hermit.config

import hermit.config.property.Property
import hermit.config.property.PropertyHolder
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

open class HermitConfiguration(val file: File) : PropertyHolder() {
    init {
        require(file.extension == "yml" || file.extension == "yaml") {
            "The configuration's file extension must either be 'yml' or 'yaml'."
        }
    }


    override val yaml = YamlConfiguration.loadConfiguration(file)
    override val properties = mutableSetOf<Property<*>>()

    fun reload() {
        yaml.load(file)
        properties.forEach(Property<*>::reload)
    }

    fun save() {
        yaml.save(file)
    }

    fun delete() {
        file.delete()
    }
}