package hermit

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class HermitConfiguration(val file: File) : YamlConfiguration() {
    fun reload() {
        load(file)
    }

    fun delete() {
        file.delete()
    }
}