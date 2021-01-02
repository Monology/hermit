package hermit.command.argument

import hermit.command.Pass
import hermit.command.Result
import org.bukkit.command.CommandSender

class EitherArgument<L : Any, R : Any>(val left: Argument<L>, val right: Argument<R>) : Argument<Either<L, R>>() {
    override var name = left.name + " or " + right.name

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>): Result {
        return left.validate(sender, index, args) or right.validate(sender, index, args)
    }

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): Either<L, R> {
        return if (left.validate(sender, index, args) == Pass) {
            Either.Left(left.extract(sender, index, args))
        } else {
            Either.Right(right.extract(sender, index, args))
        }
    }
}

sealed class Either<L, R>(val value: Any?) {
    class Left<L, R>(val left: L) : Either<L, R>(left)
    class Right<L, R>(val right: R) : Either<L, R>(right)
}

infix fun <L : Argument<*>, R : Argument<*>> L.or(right: R) = EitherArgument(this, right)