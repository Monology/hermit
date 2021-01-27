package hermit.config.property

class MapListProperty(default: List<Map<*, *>>, configuration: PropertyHolder) : MutableProperty<List<Map<*, *>>>(default, configuration) {
    override fun read() = configuration.yaml.getMapList(name)
}

fun PropertyHolder.mapList(default: List<Map<*, *>>) = MapListProperty(default, this)