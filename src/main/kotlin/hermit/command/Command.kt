package hermit.command

import hermit.HermitPlugin
import hermit.command.argument.*
import org.bukkit.command.CommandSender

class CommandDsl<T : HermitPlugin>(val plugin: T, val label: String, var aliases: Set<String>, @PublishedApi internal val level: Int) {
    @PublishedApi internal var executor: (CommandSender, Array<String>) -> Unit = { _, _ -> }
    @PublishedApi internal var tab: ((CommandSender, Array<String>) -> List<String>)? = null
    @PublishedApi internal var baseUsage: String = label
    @PublishedApi internal var usages = mutableListOf<String>()

    var description = "A command."
    var permission: String? = null

    @PublishedApi internal fun <T : Any> Argument<T>.extractSafely(sender: CommandSender, index: Int, args: MutableList<String?>, total: Int): T? {
        if (args.size < index + 1) {
            plugin.commandMessages.invalidArgumentAmount(sender, args.size, total)
        }

        return when (val result = validate(sender, index, args)) {
            Pass -> extract(sender, index, args)
            is Fail -> {
                result.block(sender)
                null
            }
        }
    }

    inline fun sub(label: String, vararg aliases: String, block: CommandDsl<T>.() -> Unit) {
        val command = CommandDsl(plugin, label, aliases.toSet(), level + 1).apply(block)

        usages.add("$baseUsage ${command.baseUsage}")

        executor = block@{ sender, args ->
            if (args[0] == label) {
                if (command.permission?.let { sender.hasPermission(it) } == true) {
                    if (!plugin.getCommand(label)!!.testPermission(sender)) {
                        return@block
                    }
                }

                command.executor(sender, args.drop(level).toTypedArray())
            } else {
                executor(sender, args)
            }
        }
    }

    inline fun <reified CS : CommandSender> execute(crossinline block: CommandContext<Arguments.None, CS>.() -> Unit) {
        baseUsage = generateCommandUsage(label)
        executor = block@{ sender, _ ->
            if (sender !is CS) return@block

            block(CommandContext(sender, Arguments.None))
        }
    }

    inline fun <reified CS : CommandSender, A : Any> execute(argument1: Argument<A>, crossinline block: CommandContext<Arguments.One<A>, CS>.() -> Unit) {
        baseUsage = generateCommandUsage(label, argument1)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 1) ?: return@block
            block(CommandContext(sender, Arguments.One(a1)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        crossinline block: CommandContext<Arguments.Two<A, B>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 2) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 2) ?: return@block
            block(CommandContext(sender, Arguments.Two(a1, a2)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        crossinline block: CommandContext<Arguments.Three<A, B, C>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 3) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 3) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 3) ?: return@block
            block(CommandContext(sender, Arguments.Three(a1, a2, a3)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any, D : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        argument4: Argument<D>,
        crossinline block: CommandContext<Arguments.Four<A, B, C, D>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3, argument4)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 4) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 4) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 4) ?: return@block
            val a4 = argument4.extractSafely(sender, 3, args, 4) ?: return@block
            block(CommandContext(sender, Arguments.Four(a1, a2, a3, a4)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any, D : Any, E : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        argument4: Argument<D>,
        argument5: Argument<E>,
        crossinline block: CommandContext<Arguments.Five<A, B, C, D, E>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3, argument4, argument5)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 5) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 5) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 5) ?: return@block
            val a4 = argument4.extractSafely(sender, 3, args, 5) ?: return@block
            val a5 = argument5.extractSafely(sender, 4, args, 5) ?: return@block
            block(CommandContext(sender, Arguments.Five(a1, a2, a3, a4, a5)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any, D : Any, E : Any, F : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        argument4: Argument<D>,
        argument5: Argument<E>,
        argument6: Argument<F>,
        crossinline block: CommandContext<Arguments.Six<A, B, C, D, E, F>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3, argument4, argument5, argument6)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 6) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 6) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 6) ?: return@block
            val a4 = argument4.extractSafely(sender, 3, args, 6) ?: return@block
            val a5 = argument5.extractSafely(sender, 4, args, 6) ?: return@block
            val a6 = argument6.extractSafely(sender, 5, args, 6) ?: return@block
            block(CommandContext(sender, Arguments.Six(a1, a2, a3, a4, a5, a6)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any, D : Any, E : Any, F : Any, G : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        argument4: Argument<D>,
        argument5: Argument<E>,
        argument6: Argument<F>,
        argument7: Argument<G>,
        crossinline block: CommandContext<Arguments.Seven<A, B, C, D, E, F, G>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3, argument4, argument5, argument6, argument7)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 7) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 7) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 7) ?: return@block
            val a4 = argument4.extractSafely(sender, 3, args, 7) ?: return@block
            val a5 = argument5.extractSafely(sender, 4, args, 7) ?: return@block
            val a6 = argument6.extractSafely(sender, 5, args, 7) ?: return@block
            val a7 = argument7.extractSafely(sender, 6, args, 7) ?: return@block
            block(CommandContext(sender, Arguments.Seven(a1, a2, a3, a4, a5, a6, a7)))
        }
    }

    inline fun <reified CS : CommandSender, A : Any, B : Any, C : Any, D : Any, E : Any, F : Any, G : Any, H : Any> execute(
        argument1: Argument<A>,
        argument2: Argument<B>,
        argument3: Argument<C>,
        argument4: Argument<D>,
        argument5: Argument<E>,
        argument6: Argument<F>,
        argument7: Argument<G>,
        argument8: Argument<H>,
        crossinline block: CommandContext<Arguments.Eight<A, B, C, D, E, F, G, H>, CS>.() -> Unit
    ) {
        baseUsage = generateCommandUsage(label, argument1, argument2, argument3, argument4, argument5, argument6, argument7, argument8)
        executor = block@{ sender, args ->
            if (sender !is CS) return@block

            val args: MutableList<String?> = args.drop(level).toMutableList()
            val a1 = argument1.extractSafely(sender, 0, args, 8) ?: return@block
            val a2 = argument2.extractSafely(sender, 1, args, 8) ?: return@block
            val a3 = argument3.extractSafely(sender, 2, args, 8) ?: return@block
            val a4 = argument4.extractSafely(sender, 3, args, 8) ?: return@block
            val a5 = argument5.extractSafely(sender, 4, args, 8) ?: return@block
            val a6 = argument6.extractSafely(sender, 5, args, 8) ?: return@block
            val a7 = argument7.extractSafely(sender, 6, args, 8) ?: return@block
            val a8 = argument8.extractSafely(sender, 7, args, 8) ?: return@block
            block(CommandContext(sender, Arguments.Eight(a1, a2, a3, a4, a5, a6, a7, a8)))
        }
    }

    fun tab(block: (CommandSender, Array<String>) -> List<String>) {
        tab = block
    }

    fun tab(block: TabDsl.() -> Unit) {
        tab(TabDsl().apply(block).build())
    }

    // TODO: Improve.
    inner class TabDsl {
        private val arguments = mutableListOf<(CommandSender, Array<String>) -> List<String>>()

        fun <A> argument(block: (CommandSender) -> List<String>) {
            arguments.add { sender, _ -> block(sender) }
        }

        fun build() = { commandSender: CommandSender, args: Array<String> ->
            arguments.getOrNull(args.size)?.invoke(commandSender, args) ?: emptyList()
        }
    }

    fun build(): HermitCommand {
        return HermitCommand(label, aliases, description, permission, baseUsage, usages, tab, executor)
    }
}

inline fun <T : HermitPlugin> command(plugin: T, label: String, vararg aliases: String, block: CommandDsl<T>.() -> Unit): HermitCommand {
    return CommandDsl(plugin, label, aliases.toSet(), 0).apply(block).build()
}

@JvmName("commandReceiver")
inline fun <T : HermitPlugin> T.command(label: String, vararg aliases: String, block: CommandDsl<T>.() -> Unit): HermitCommand {
    return command(this, label, *aliases) { block() }
}