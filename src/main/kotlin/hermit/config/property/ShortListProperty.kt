package hermit.config.property

class ShortListProperty(default: List<Short>, configuration: PropertyHolder) : MutableProperty<List<Short>>(default, configuration) {
    override fun read() = configuration.yaml.getShortList(name)
}

fun PropertyHolder.shortList(default: List<Short>) = ShortListProperty(default, this)