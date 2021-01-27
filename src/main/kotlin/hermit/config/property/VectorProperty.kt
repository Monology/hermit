package hermit.config.property

import org.bukkit.util.Vector

class VectorProperty(default: Vector, configuration: PropertyHolder) : MutableProperty<Vector>(default, configuration) {
    override fun read() = configuration.yaml.getVector(name)!!
}

fun PropertyHolder.vector(default: Vector) = VectorProperty(default, this)