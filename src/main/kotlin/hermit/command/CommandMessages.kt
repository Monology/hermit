package hermit.command

import hermit.colored
import hermit.command.argument.Argument
import hermit.ordinal
import hermit.plural
import org.bukkit.command.CommandSender

open class CommandMessages {
    open fun playerIsNotOnline(receiver: CommandSender, name: String) {
        receiver.sendMessage("&cThe player $name is not online or does not exist.".colored)
    }

    open fun invalidArgumentType(receiver: CommandSender, requiredArgument: Argument<*>, index: Int, args: Array<String>) {
        receiver.sendMessage("&cInvalid arguments. This command requires ${requiredArgument.name} as its ${(index + 1).ordinal} argument. You input \"${args[index]}\".".colored)
    }

    open fun requiresPlayer(receiver: CommandSender) {
        receiver.sendMessage("&cYou must be a player to execute this command.".colored)
    }

    open fun requiresConsole(receiver: CommandSender) {
        receiver.sendMessage("&cYou must be on the server console to execute this command.".colored)
    }

    open fun invalidArgumentAmount(receiver: CommandSender, givenArguments: Int, requiredArguments: Int) {
        receiver.sendMessage("&cThis command requires $requiredArguments ${"argument".plural(requiredArguments)}, but you put in $givenArguments ${"argument".plural(givenArguments)}.".colored)
    }

    open fun unknownCommand(receiver: CommandSender, base: String, command: String) {
        receiver.sendMessage("&cUnknown sub command. \"/$base $command\"".colored)
    }
}