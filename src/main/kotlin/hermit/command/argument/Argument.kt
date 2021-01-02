package hermit.command.argument

import hermit.command.Result
import org.bukkit.command.CommandSender

abstract class Argument<T : Any> {
    abstract var name: String

    abstract fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result

    abstract fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): T
}

infix fun <T : Argument<*>> T.named(name: String) {
    this.name = name
}