package hermit.config.property

class IntListProperty(default: List<Int>, configuration: PropertyHolder) : MutableProperty<List<Int>>(default, configuration) {
    override fun read() = configuration.yaml.getIntegerList(name)
}

fun PropertyHolder.intList(default: List<Int>) = IntListProperty(default, this)