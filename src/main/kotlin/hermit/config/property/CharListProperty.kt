package hermit.config.property

class CharListProperty(default: List<Char>, configuration: PropertyHolder) : MutableProperty<List<Char>>(default, configuration) {
    override fun read() = configuration.yaml.getCharacterList(name)
}

fun PropertyHolder.charList(default: List<Char>) = CharListProperty(default, this)