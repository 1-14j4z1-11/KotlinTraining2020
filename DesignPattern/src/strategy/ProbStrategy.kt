package strategy

import kotlin.random.Random

class ProbStrategy(seed: Int) : Strategy {
    private val random = Random(seed)
    private var prevHand = Hand.ROCK
    private var currentHand = Hand.ROCK
    private val history = mapOf(
            Pair(Hand.ROCK, mutableMapOf(Pair(Hand.ROCK, 1), Pair(Hand.SCISSORS, 1), Pair(Hand.PAPER, 1))),
            Pair(Hand.SCISSORS, mutableMapOf(Pair(Hand.ROCK, 1), Pair(Hand.SCISSORS, 1), Pair(Hand.PAPER, 1))),
            Pair(Hand.PAPER, mutableMapOf(Pair(Hand.ROCK, 1), Pair(Hand.SCISSORS, 1), Pair(Hand.PAPER, 1)))
    )

    override fun nextHand(): Hand {
        val bet: Int = random.nextInt(this.getSum(this.currentHand))
        val nextHand = when {
            (bet < this.getHistory(this.currentHand, Hand.ROCK)) -> Hand.ROCK
            (bet < this.getHistory(this.currentHand, Hand.ROCK) + this.getHistory(this.currentHand, Hand.SCISSORS)) -> Hand.SCISSORS
            else -> Hand.PAPER
        }

        this.prevHand = this.currentHand
        this.currentHand = nextHand
        return nextHand
    }

    private fun getSum(hand: Hand): Int {
        return Hand.values().map { this.getHistory(hand, it) }.sumBy { it }
    }

    override fun study(win: Boolean) {
        if (win) {
            this.incrementHistory(this.prevHand, this.currentHand)
        } else {
            for(other in this.currentHand.others()) {
                this.incrementHistory(this.prevHand, other)
            }
        }
    }

    private fun getHistory(prevHand: Hand, currentHand: Hand) : Int {
        return this.history[prevHand]?.get(currentHand)!!
    }

    private fun incrementHistory(prevHand: Hand, currentHand: Hand) {
        this.history[prevHand]?.set(currentHand, this.getHistory(prevHand, currentHand) + 1)
    }
}