package hermit.config.property

class StringProperty(default: String, configuration: PropertyHolder) : MutableProperty<String>(default, configuration) {
    override fun read() = configuration.yaml.getString(name)!!
}

fun PropertyHolder.string(default: String) = StringProperty(default, this)