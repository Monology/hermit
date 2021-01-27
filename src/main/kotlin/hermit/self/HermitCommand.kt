package hermit.self

import hermit.command.command
import org.bukkit.entity.Player

internal fun Hermit.hermitCommand() = command("hermit") {
    permission = "hermit.admin"

    sub("reload") {
        permission = "hermit.admin.reload"

        execute<Player> {
            plugin.reloadConfig()
        }
    }

    execute<Player> {
        respond {
            - "&c"
        }
    }
}