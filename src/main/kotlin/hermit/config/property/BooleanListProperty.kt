package hermit.config.property

class BooleanListProperty(default: List<Boolean>, configuration: PropertyHolder) : MutableProperty<List<Boolean>>(default, configuration) {
    override fun read() = configuration.yaml.getBooleanList(name)
}

fun PropertyHolder.booleanList(default: List<Boolean>) = BooleanListProperty(default, this)