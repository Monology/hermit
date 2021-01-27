package hermit.config.property

class FloatListProperty(default: List<Float>, configuration: PropertyHolder) : MutableProperty<List<Float>>(default, configuration) {
    override fun read() = configuration.yaml.getFloatList(name)
}

fun PropertyHolder.floatList(default: List<Float>) = FloatListProperty(default, this)