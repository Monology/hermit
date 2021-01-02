package hermit.command

import hermit.HermitStringBuilder
import hermit.colored
import hermit.command.argument.Arguments
import hermit.msg
import org.bukkit.command.CommandSender
import java.lang.StringBuilder

class CommandContext<T : Arguments, C : CommandSender>(val sender: C, val args: T) {
    inline fun respond(any: Any?) {
        sender.sendMessage(any.toString().colored)
    }

    inline fun respond(block: HermitStringBuilder.() -> Unit) {
        HermitStringBuilder(StringBuilder())
            .apply(block)
            .stringBuilder
            .lines()
            .forEach(this::respond)
    }
}