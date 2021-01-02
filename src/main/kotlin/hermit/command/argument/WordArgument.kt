package hermit.command.argument

import hermit.command.Pass
import hermit.command.Result
import org.bukkit.command.CommandSender

class WordArgument : Argument<String>() {
    override var name = "a word"

    override fun validate(sender: CommandSender, index: Int, args: MutableList<String?>) = Pass

    override fun extract(sender: CommandSender, index: Int, args: MutableList<String?>): String {
        return args[index]!!
    }
}