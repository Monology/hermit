package hermit.item

import hermit.HermitPlugin
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class Item(private val plugin: HermitPlugin, private val itemStack: ItemStack) {
    constructor(plugin: HermitPlugin, material: Material, count: Int = 1): this(plugin, ItemStack(material, count))

    val meta = itemMeta[itemStack] as ItemMeta

    companion object {
        private val itemMeta = ItemStack::class.java.getDeclaredField("meta")

        init {
            itemMeta.isAccessible = true
        }
    }
}