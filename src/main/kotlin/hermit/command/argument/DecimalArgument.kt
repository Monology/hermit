package hermit.command.argument

import hermit.command.Fail
import hermit.command.Pass
import hermit.command.Result
import hermit.command.passOrNull
import hermit.name
import org.bukkit.command.CommandSender

class DecimalArgument(var range: ClosedFloatingPointRange<Double> = Double.MIN_VALUE..Double.MAX_VALUE) : Argument<Double>() {
    override var name = "a decimal"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        val long = args[index]?.toDoubleOrNull() ?: return Fail("\"${args[index]}\" is not a valid decimal.")

        if (long !in range) {
            return Fail("$long is not ${range.name()}.")
        }

        return Pass
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): Double {
        return args[index]!!.toDouble()
    }
}

infix fun DecimalArgument.range(range: ClosedFloatingPointRange<Double>) = apply {
    this.range = range
}