package hermit.config.property

class LongListProperty(default: List<Long>, configuration: PropertyHolder) : MutableProperty<List<Long>>(default, configuration) {
    override fun read() = configuration.yaml.getLongList(name)
}

fun PropertyHolder.longList(default: List<Long>) = LongListProperty(default, this)