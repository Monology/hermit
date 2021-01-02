package hermit.command

import hermit.command.argument.Argument

fun generateCommandUsage(label: String): String {
    return "/$label"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>
): String {
    return "$label ${a1.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>
): String {
    return generateCommandUsage(label, a1) + " ${a2.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2) + " ${a3.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>,
    a4: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2, a3) + " ${a4.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>,
    a4: Argument<*>,
    a5: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2, a3, a4) + " ${a5.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>,
    a4: Argument<*>,
    a5: Argument<*>,
    a6: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2, a3, a4, a5) + " ${a6.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>,
    a4: Argument<*>,
    a5: Argument<*>,
    a6: Argument<*>,
    a7: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2, a3, a4, a5, a6) + " ${a7.usage}"
}

fun generateCommandUsage(
    label: String,
    a1: Argument<*>,
    a2: Argument<*>,
    a3: Argument<*>,
    a4: Argument<*>,
    a5: Argument<*>,
    a6: Argument<*>,
    a7: Argument<*>,
    a8: Argument<*>
): String {
    return generateCommandUsage(label, a1, a2, a3, a4, a5, a6, a7) + " ${a8.usage}"
}

private val Argument<*>.usage get(): String {
    return "<" + (if (name != null) "$name: " else "") + typeName + ">"
}
