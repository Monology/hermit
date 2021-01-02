package hermit.command.argument

import hermit.command.Fail
import hermit.command.Result
import hermit.command.passOrNull
import hermit.toUUID
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayerArgument : Argument<Player>() {
    override var name = "an online player's name"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        val argument = args[index]!!

        return if (argument.length > 16) {
            Bukkit.getPlayer(argument.toUUID()).passOrNull() ?: Fail("The given username is either too long, or the given UUID is invalid.")
        } else {
            Bukkit.getPlayer(argument).passOrNull() ?: Fail("The given username is invalid.")
        }
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): Player {
        val argument = args[index]!!

        return if (argument.length > 16) {
            Bukkit.getPlayer(argument.toUUID())
        } else {
            Bukkit.getPlayer(argument)
        }!!
    }
}