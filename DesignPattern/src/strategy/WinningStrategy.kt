package strategy

import kotlin.random.Random

class WinningStrategy(seed: Int) : Strategy {
    private val random = Random(seed)
    private var won = false
    private var prevHand: Hand = Hand.ROCK

    override fun nextHand(): Hand {
        if (!this.won) {
            this.prevHand = this.random.nextHand()
        }
        return this.prevHand
    }

    override fun study(win: Boolean) {
        this.won = win
    }
}