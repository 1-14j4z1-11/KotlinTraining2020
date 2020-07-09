package strategy

import kotlin.random.Random

enum class Hand(private val str: String, private val value: Int, private val weak: Int, private val strong: Int) {
    ROCK("グー", 0, 2, 1),
    SCISSORS("チョキ", 1, 0, 2),
    PAPER("パー", 2, 1, 0);

    fun isStrongerThan(hand: Hand) : Boolean {
        return (hand.value == this.strong)
    }

    fun isWeakerThan(hand: Hand) : Boolean {
        return (hand.value == this.weak)
    }

    override fun toString(): String {
        return this.str
    }

    fun others() : List<Hand> {
        return values().asIterable().minus(this)
    }
}

fun Random.nextHand() : Hand {
    val values = Hand.values()
    return values[Random.nextInt(values.size)]
}
