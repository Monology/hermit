package hermit.worldgeneration

import org.bukkit.World
import org.bukkit.generator.ChunkGenerator
import java.util.*

class WorldGenerator {
    fun toChunkGenerator() = WorldGeneratorApi
}

fun worldGenerator(block: WorldGenerator.() -> Unit): ChunkGenerator {
    return WorldGenerator().apply(block).toChunkGenerator()
}