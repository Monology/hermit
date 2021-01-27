package hermit.config.property

class LongProperty(default: Long, configuration: PropertyHolder) : MutableProperty<Long>(default, configuration) {
    override fun read() = configuration.yaml.getLong(name)
}

fun PropertyHolder.long(default: Long) = LongProperty(default, this)