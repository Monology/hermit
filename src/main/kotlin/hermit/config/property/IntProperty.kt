package hermit.config.property

class IntProperty(default: Int, configuration: PropertyHolder) : MutableProperty<Int>(default, configuration) {
    override fun read() = configuration.yaml.getInt(name)
}

fun PropertyHolder.int(default: Int) = IntProperty(default, this)