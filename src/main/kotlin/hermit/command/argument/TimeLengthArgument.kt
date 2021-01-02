package hermit.command.argument

import hermit.*
import hermit.command.Fail
import hermit.command.Pass
import hermit.command.Result
import org.bukkit.command.CommandSender

/**
 * A length of time in ticks. Multiply by 50 to get milliseconds. Divide by 20 to get seconds.
 */
class TimeLengthArgument : Argument<Long>() {
    override var name = "an amount of time (10s, 5m, 3h, etc.)"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        val argument = args[index]!!
        val suffix = argument.last()

        val numberString = argument.dropLast(1)
        numberString.toLongOrNull() ?: return Fail("\"$numberString\" is not a valid whole number.")

        return if (suffix in suffixes) {
            Pass
        } else {
            Fail("'$suffix' is not a valid suffix. The available suffixes are 's' (seconds), 'm' (minutes), 'h' (hours), 'd' (days), 'w' (weeks), 'M' (months), 'y' (years).")
        }
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): Long {
        val argument = args[index]!!
        val number = argument.dropLast(1).toLong()

        return when (argument.last()) {
            's' -> number.seconds
            'm' -> number.minutes
            'h' -> number.hours
            'd' -> number.days
            'w' -> number.weeks
            'M' -> number.months
            'y' -> number.years
            else -> throw internalError("Argument validation has failed.")
        }
    }

    companion object {
        private val suffixes = setOf('s', 'm', 'h', 'd', 'w', 'M', 'y')
    }
}