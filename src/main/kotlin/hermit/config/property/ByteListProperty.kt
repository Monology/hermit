package hermit.config.property

class ByteListProperty(default: List<Byte>, configuration: PropertyHolder) : MutableProperty<List<Byte>>(default, configuration) {
    override fun read() = configuration.yaml.getByteList(name)
}

fun PropertyHolder.byteList(default: List<Byte>) = ByteListProperty(default, this)