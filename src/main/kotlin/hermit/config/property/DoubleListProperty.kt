package hermit.config.property

class DoubleListProperty(default: List<Double>, configuration: PropertyHolder) : MutableProperty<List<Double>>(default, configuration) {
    override fun read() = configuration.yaml.getDoubleList(name)
}

fun PropertyHolder.doubleList(default: List<Double>) = DoubleListProperty(default, this)