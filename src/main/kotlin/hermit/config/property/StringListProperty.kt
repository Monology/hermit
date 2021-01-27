package hermit.config.property

class StringListProperty(default: List<String>, configuration: PropertyHolder) : MutableProperty<List<String>>(default, configuration) {
    override fun read() = configuration.yaml.getStringList(name)
}

fun PropertyHolder.stringList(default: List<String>) = StringListProperty(default, this)