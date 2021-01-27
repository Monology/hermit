package hermit.config.property

import hermit.config.HermitConfiguration
import org.bukkit.configuration.serialization.ConfigurationSerializable
import kotlin.reflect.KClass

class SerializableProperty<T : ConfigurationSerializable>(`class`: KClass<T>, default: T, configuration: PropertyHolder) : MutableProperty<T>(default, configuration) {
    private val javaClass = `class`.java

    override fun read() = configuration.yaml.getSerializable(name, javaClass)!!
}

inline fun <reified T : ConfigurationSerializable> PropertyHolder.serializable(default: T) = SerializableProperty(T::class, default, this)