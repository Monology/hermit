package hermit.config.property

class BooleanProperty(default: Boolean, configuration: PropertyHolder) : MutableProperty<Boolean>(default, configuration) {
    override fun read() = configuration.yaml.getBoolean(name)
}

fun PropertyHolder.boolean(default: Boolean) = BooleanProperty(default, this)