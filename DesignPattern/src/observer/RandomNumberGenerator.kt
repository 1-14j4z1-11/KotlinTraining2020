package observer

import kotlin.properties.Delegates
import kotlin.random.Random

class RandomNumberGenerator : NumberGenerator() {
    private val random = Random(1)

    override var number: Int by Delegates.observable(0) { _, _, _ -> this.notifyObservers() }
        private set

    override fun execute() {
        for (i in 0..19) {
            this.number = this.random.nextInt(50)
        }
    }
}