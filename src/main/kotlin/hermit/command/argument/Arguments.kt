package hermit.command.argument

sealed class Arguments {
    object None : Arguments()

    data class One<A>(val first: A) : Arguments()

    data class Two<A, B>(val first: A, val second: B) : Arguments()

    data class Three<A, B, C>(val first: A, val second: B, val third: C) : Arguments()

    data class Four<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D) : Arguments()

    data class Five<A, B, C, D, E>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E) : Arguments()

    data class Six<A, B, C, D, E, F>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E, val sixth: F) : Arguments()

    data class Seven<A, B, C, D, E, F, G>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E, val sixth: F, val seventh: G) : Arguments()

    data class Eight<A, B, C, D, E, F, G, H>(val first: A, val second: B, val third: C, val fourth: D, val fifth: E, val sixth: F, val seventh: G, val eighth: H) : Arguments()

    data class Varargs(val args: List<String>)
}