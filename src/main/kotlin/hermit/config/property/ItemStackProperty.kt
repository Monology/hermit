package hermit.config.property

import org.bukkit.inventory.ItemStack

class ItemStackProperty(default: ItemStack, configuration: PropertyHolder) : MutableProperty<ItemStack>(default, configuration) {
    override fun read() = configuration.yaml.getItemStack(name)!!
}

fun PropertyHolder.itemStack(default: ItemStack) = ItemStackProperty(default, this)