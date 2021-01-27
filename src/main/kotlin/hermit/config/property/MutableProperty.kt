package hermit.config.property

import kotlin.reflect.KProperty

abstract class MutableProperty<T>(val default: T, configuration: PropertyHolder) : Property<T>(configuration) {
    override var value = default

    operator fun setValue(thisRef: PropertyHolder, property: KProperty<*>, value: T) {
        this.value = value
        set(value)
    }

    final override operator fun provideDelegate(thisRef: PropertyHolder, property: KProperty<*>): MutableProperty<T> {
        super.provideDelegate(thisRef, property)
        set(default)
        return this
    }

    protected abstract fun read(): T

    open fun set(value: T) {
        configuration.yaml.set(name, value)
    }

    override fun reload() {
        value = read()
    }

    init {
        configuration.properties.add(this)
    }
}