package hermit.command.argument

import hermit.command.Fail
import hermit.command.Pass
import hermit.command.Result
import hermit.name
import org.bukkit.command.CommandSender

class IntegerArgument(var range: LongRange = Long.MIN_VALUE..Long.MAX_VALUE) : Argument<Long>() {
    override var name = "a whole number"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        val long = args[index]?.toLongOrNull() ?: return Fail("\"${args[index]}\" is not a valid whole number.")

        if (long !in range) {
            return Fail("$long is not ${range.name()}.")
        }

        return Pass
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): Long {
        return args[index]!!.toLong()
    }
}

infix fun IntegerArgument.range(range: LongRange) = apply {
    this.range = range
}