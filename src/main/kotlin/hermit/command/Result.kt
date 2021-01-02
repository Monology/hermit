package hermit.command

import hermit.msg
import org.bukkit.command.CommandSender

sealed class Result {
    infix fun or(that: Result): Result {
        return if (this is Pass) that else this
    }

    infix fun and(that: Result): Result {
        return if (this !is Pass) this else that
    }

    infix fun nor(that: Result): Boolean {
        return this !is Pass && that !is Pass
    }
}

object Pass : Result()

fun <T : Any> T?.passOrNull() = if (this == null) Pass else null

open class Fail(val block: CommandSender.() -> Unit) : Result() {
    constructor(message: String): this({
        msg(message)
    })
}