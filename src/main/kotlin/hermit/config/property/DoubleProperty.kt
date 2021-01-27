package hermit.config.property

class DoubleProperty(default: Double, configuration: PropertyHolder) : MutableProperty<Double>(default, configuration) {
    override fun read() = configuration.yaml.getDouble(name)
}

fun PropertyHolder.double(default: Double) = DoubleProperty(default, this)