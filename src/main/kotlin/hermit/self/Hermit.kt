package hermit.self

import hermit.HermitPlugin

internal class Hermit : HermitPlugin() {
    override fun enable() {
        hermitCommand().register()
    }
}