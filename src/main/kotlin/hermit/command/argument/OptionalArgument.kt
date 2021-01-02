package hermit.command.argument

import hermit.command.Pass
import hermit.command.Result
import org.bukkit.command.CommandSender

class OptionalArgument<T : Any>(val argument: Argument<T>, val default: T) : Argument<T>() {
    override var name = argument.name + " (optionally)"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        if (argument.validate(sender, index, args) != Pass) args.add(index, null)
        return Pass
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): T {
        return if (args[index] == null) {
            default
        } else {
            argument.extract(sender, index, args)
        }
    }
}

infix fun <T : Argument<R>, R : Any> T.optionally(default: R): OptionalArgument<R> {
    return OptionalArgument(this, default)
}