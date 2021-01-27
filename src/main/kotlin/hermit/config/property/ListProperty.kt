package hermit.config.property

class ListProperty(default: List<*>, configuration: PropertyHolder) : MutableProperty<List<*>>(default, configuration) {
    override fun read() = configuration.yaml.getList(name)!!
}

fun PropertyHolder.list(default: List<*>) = ListProperty(default, this)