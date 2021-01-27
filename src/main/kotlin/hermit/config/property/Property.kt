package hermit.config.property

import kotlin.reflect.KProperty

abstract class Property<T>(protected val configuration: PropertyHolder) {
    protected abstract val value: T

    lateinit var name: String
        private set

    abstract fun reload()

    operator fun getValue(thisRef: PropertyHolder, property: KProperty<*>): T {
        return value
    }

    open operator fun provideDelegate(thisRef: PropertyHolder, property: KProperty<*>): Property<T> {
        name = property.name
        return this
    }

    init {
        configuration.properties.add(this)
    }
}