package hermit.config.property

import org.bukkit.configuration.ConfigurationSection
import kotlin.reflect.KProperty

open class Section : PropertyHolder() {
    override lateinit var yaml: ConfigurationSection

    override val properties = mutableSetOf<Property<*>>()
}

class SectionProperty<T : Section>(override val value: T, configuration: PropertyHolder) : Property<T>(configuration) {
    override fun reload() {
        value.yaml = configuration.yaml.getConfigurationSection(name)!!
    }

    override fun provideDelegate(thisRef: PropertyHolder, property: KProperty<*>) = super.provideDelegate(thisRef, property).also {
        reload()
    }
}

fun <T : Section> PropertyHolder.section(section: T) = SectionProperty(section, this)